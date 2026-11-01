package lab06;

import lab06.annotations.Component;
import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ComponentScanner {

    private ComponentScanner() {
    }

    public static List<Class<?>> scanPackage(String packageName) throws Exception {
        String path = packageName.replace('.', '/');
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> classes = new ArrayList<>();

        Enumeration<URL> resources = loader.getResources(path);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if ("file".equals(url.getProtocol())) {
                scanDirectory(new File(url.toURI()), packageName, loader, classes);
            } else if ("jar".equals(url.getProtocol())) {
                scanJar(url, path, loader, classes);
            }
        }
        classes.sort(Comparator.comparing(Class::getName));
        return classes;
    }

    public static List<Class<?>> findComponents(List<Class<?>> allClasses) {
        List<Class<?>> result = new ArrayList<>();
        for (Class<?> c : allClasses) {
            if (c.isAnnotationPresent(Component.class)) {
                result.add(c);
            }
        }
        return result;
    }

    private static void scanDirectory(File dir, String packageName, ClassLoader loader,
                                      List<Class<?>> out) throws ClassNotFoundException {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            String name = file.getName();
            if (file.isFile() && name.endsWith(".class") && !name.contains("$")) {
                out.add(Class.forName(packageName + "." + name.substring(0, name.length() - 6), false, loader));
            }
        }
    }

    private static void scanJar(URL url, String path, ClassLoader loader,
                                List<Class<?>> out) throws Exception {
        JarURLConnection connection = (JarURLConnection) url.openConnection();
        connection.setUseCaches(false);
        try (JarFile jar = connection.getJarFile()) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(path + "/") && name.endsWith(".class") && !name.contains("$")
                        && name.indexOf('/', path.length() + 1) < 0) {
                    String className = name.substring(0, name.length() - 6).replace('/', '.');
                    out.add(Class.forName(className, false, loader));
                }
            }
        }
    }
}
