package com.array.commonmodule.bean;

/**
 * @author yee
 * 测试用例
 */
public class UseCase {
    private Long useCaseId;
    private Long questionId;
    private String input;
    private String output;

    public UseCase() {
    }

    public UseCase(Long useCaseId, Long questionId, String input, String output) {
        this.useCaseId = useCaseId;
        this.questionId = questionId;
        this.input = input;
        this.output = output;
    }

    @Override
    public String toString() {
        return "UseCase{" +
                "useCaseId='" + useCaseId + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }

    public Long getUseCaseId() {
        return useCaseId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setUseCaseId(Long useCaseId) {
        this.useCaseId = useCaseId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
