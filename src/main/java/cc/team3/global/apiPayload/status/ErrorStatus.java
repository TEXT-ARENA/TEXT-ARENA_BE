package cc.team3.global.apiPayload.status;

import cc.team3.global.apiPayload.code.BaseErrorCode;
import cc.team3.global.apiPayload.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // Equipment 관련
    EQUIPMENT_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "EQUIPMENT_401", "해당 타입을 찾을 수 없습니다"),
    EQUIPMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "EQUIPMENT_402", "해당 값을 찾을 수 없습니다."),
    EQUIPMENT_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "EQUIPMENT_403", "같은 종류의 장비가 이미 존재합니다."),

    // User 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_401", "해당 유저를 찾을 수 없습니다."),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "USER_402", "비밀번호가 일치하지 않습니다."),

    // Character 관련
    CHARACTER_NOT_FOUND(HttpStatus.NOT_FOUND, "CHARACTER_401", "해당 캐릭터를 찾을 수 없습니다."),
    EMPTY_BATTLE_OPPONENT(HttpStatus.NOT_FOUND, "CHARACTER_402", "대결할 상대가 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
