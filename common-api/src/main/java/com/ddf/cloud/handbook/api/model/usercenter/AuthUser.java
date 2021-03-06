package com.ddf.cloud.handbook.api.model.usercenter;

import com.ddf.cloud.handbook.core.entity.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 认证用户实体
 *
 * @author dongfang.ding
 * @date 2020/6/29 0029 10:37
 **/
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor 
@Data
@ApiModel("用户")
@Accessors(chain = true)
public class AuthUser extends BaseDomain implements Serializable {

    static final long serialVersionUID = -5091699981026819031L;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("用户随机码，生成密码的盐，注册时生成且不可变！")
	private String userToken;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("最后一次修改密码的时间")
    private Long lastModifyPassword;

    @ApiModelProperty("上次登录密码的时间")
    private Long lastLoginTime;

}