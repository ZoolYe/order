package zool.order.utils;

import zool.order.Vo.ResultVo;

public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = ResultVo.builder()
                .code(0)
                .msg("成功")
                .data(object).build();
        return resultVo;
    }

}
