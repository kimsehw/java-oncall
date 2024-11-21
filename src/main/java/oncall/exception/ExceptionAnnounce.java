package oncall.exception;

public enum ExceptionAnnounce {

    NAME_LENGTH(" 닉네임은 1 ~ 5자만 가능합니다."),
    MORE_EMPLOY(" 비상 근무 제도 유지를 위해 최소 5명 근무자가 유지되도록 신규 채용해주세요."),
    TOO_MUCH_WORKER(" 유휴 군무자 방지를 위해 최대 35명이 넘지 않도록 관리해주세요."),
    DUPLICATE_NAME(" 닉네임은 중복 될 수 없으며, 같은 인원이 두 번 편성될 수 없습니다."),
    WEEK_HOLIDAY_DIFF(" 평일과 휴일의 근무자가 같아야 합니다.");

    private final String message;

    ExceptionAnnounce(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}