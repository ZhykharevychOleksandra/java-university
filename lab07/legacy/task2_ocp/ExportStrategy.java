package lab07.legacy.task2_ocp;
public interface ExportStrategy {

    String export(ReportData data);

    String getFormatName();
}