package lab07.legacy.task4_isp;

public interface UserAuthentication {
    boolean authenticate(String userIdentifier, String secretKey);
}