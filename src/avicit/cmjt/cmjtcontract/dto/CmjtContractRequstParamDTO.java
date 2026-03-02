package avicit.cmjt.cmjtcontract.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("时间范围查询参数")
public class CmjtContractRequstParamDTO {
    @ApiModelProperty(value = "开始时间(格式: yyyy-MM-dd)", example = "2023-01-01", required = true)
    private String startTime;

    @ApiModelProperty(value = "结束时间(格式: yyyy-MM-dd)", example = "2023-12-31", required = true)
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }



}
