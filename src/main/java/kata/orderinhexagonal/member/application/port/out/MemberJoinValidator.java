package kata.orderinhexagonal.member.application.port.out;

public interface MemberJoinValidator {
	boolean existsEmail(String email);
}
