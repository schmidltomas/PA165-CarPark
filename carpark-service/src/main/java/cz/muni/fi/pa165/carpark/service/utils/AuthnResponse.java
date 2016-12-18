package cz.muni.fi.pa165.carpark.service.utils;

public enum AuthnResponse {
    OK("OK"), NOT_FOUND("NOT_FOUND"), INCORRECT_PASSWORD("INCORRECT_PASSWORD");

    private String response;

    AuthnResponse(String role) {
        this.response = role;
    }

    public String getResponse() {
        return this.response;
    }

    @Override
    public String toString() {
        return getResponse();
    }
}
