package kata.orderinhexagonal.member.application.port.out;

public interface MemberJoinValidator {
	boolean verifyExistsEmail(String email);
}
