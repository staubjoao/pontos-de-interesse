package response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Response<T> {

    private T data;
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

}