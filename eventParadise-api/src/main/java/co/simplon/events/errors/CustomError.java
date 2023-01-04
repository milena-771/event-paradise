package co.simplon.events.errors;

public class CustomError {

    private String code;

    private String fieldName;

    public CustomError(String code, String fieldName) {
	this.code = code;
	this.fieldName = fieldName;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getFieldName() {
	return fieldName;
    }

    public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
    }

    @Override
    public String toString() {
	return " {code=" + code + ", fieldName=" + fieldName
		+ "}";
    }

}
