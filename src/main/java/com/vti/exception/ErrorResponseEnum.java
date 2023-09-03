package com.vti.exception;

public enum ErrorResponseEnum {
    NOT_FOUND_SONG(404, "Không tìm thấy bài hát"),
    NOT_FOUND_ACCOUNT(404, "Không tìm thấy người dùng"),
    USERNAME_EXISTED(400, "Username đã tồn tại!"),
    NAME_SONG_EXISTED(400, "Tên bài hát đã tồn tại"),
    NAME_SINGER_EXISTED(400, "Tên ca sĩ đã tồn tại"),
    LOGIN_FAIL_USERNAME(401,"Thông tin username sai"),
    LOGIN_FAIL_PASSWORD(401,"Thông tin password sai"),
    NOT_FOUND_SINGER_NAME(404,"Không tìm thấy tên ca sỹ"),
    LOGIN_FAIL(401,"Tài khoản chưa kích hoạt");

    public final int status;
    public final String message;
    ErrorResponseEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
