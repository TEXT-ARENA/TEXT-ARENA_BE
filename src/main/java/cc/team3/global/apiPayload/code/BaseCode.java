package cc.team3.global.apiPayload.code;

import cc.team3.global.apiPayload.dto.ReasonDTO;

public interface BaseCode {

    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();
}
