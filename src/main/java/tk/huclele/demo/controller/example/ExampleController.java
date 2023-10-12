package tk.huclele.demo.controller.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.huclele.demo.dto.ExampleRequestRecordDto;
import tk.huclele.demo.dto.GlobalResponseDto;
import tk.huclele.demo.version.IApiV1;

import static tk.huclele.demo.dto.GlobalResponseDto.success;


/**
 * 示例Controller.
 *
 * @author xuelele
 * @version 1.0
 * @since 2023/9/28 17:11
 */
@RestController
@Tag(name = "示例接口类对象")
public class ExampleController implements IApiV1 {

    /**
     * 示例接口 - query参数请求
     * <p>
     * 示例接口-示例请求方
     *
     * @param name 姓名
     * @return GlobalResponseDto<String>类型的返回值，返回姓名和一个需求的字符串
     */
    @Operation(summary = "示例接口 - query参数请求", description = "示例接口-示例请求方")
    @GetMapping("/example/request")
    public GlobalResponseDto<String> createExampleRequest(@Parameter(name = "name", description = "姓名", in = ParameterIn.QUERY, required = true) String name) {
        return success(name + "， 我是示例接口类实现类,我有这么一个需求");
    }

    /**
     * 示例接口 - body对象请求
     * <p>
     * 示例接口-示例请求方
     *
     * @param exampleRequestRecordDto the example request DTO
     * @return the global response DTO containing a string
     */

    @Operation(summary = "示例接口 - body对象请求", description = "示例接口-示例请求方")
    @PostMapping("/example/request/dto")
    public GlobalResponseDto<String> createExampleRequestByDto(@Validated @RequestBody ExampleRequestRecordDto exampleRequestRecordDto) {
        return success(exampleRequestRecordDto + "， 我是示例接口类实现类,我有这么一个需求");
    }

    /**
     * 示例接口 - path参数请求
     * <p>
     * 示范接口-示例请求方
     *
     * @param id 身份标识
     * @return GlobalResponseDto<String> 类型的返回值
     */
    @Operation(summary = "示例接口 - path参数请求", description = "示例接口-示例请求方")
    @GetMapping("/example/request/path/{id}")
    public GlobalResponseDto<String> createExampleRequestByPath(@Parameter(name = "id", description = "身份标识", in = ParameterIn.PATH, required = true) @PathVariable int id) {
        return success(id + "， 我是示例接口类实现类,我有这么一个需求");
    }
}
