package tk.huclele.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import tk.huclele.demo.annotations.OnlySpecifyValue;

import java.io.Serializable;

/**
 * 示例请求对象.
 *
 * @author xuelele
 * @version 1.0
 * @since 2023/10/12 10:51
 */
@Schema(name = "示例请求对象", description = "示例请求对象")
public record ExampleRequestRecordDto(
        @Schema(description = "示例url", example = "https://www.baidu.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String requestUrl,
        @Schema(description = "平台")
        String platform,
        @Schema(description = "目的地")
        @OnlySpecifyValue(strValues = {"beijing", "shanghai"})
        String destination) implements Serializable {
}
