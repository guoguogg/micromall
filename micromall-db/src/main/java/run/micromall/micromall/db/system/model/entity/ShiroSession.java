package run.micromall.micromall.db.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * ShiroSession
 *
 * @author Administrator
 * @since 2020/11/7
 */
@Data
@TableName("shiro_session")
@AllArgsConstructor
public class ShiroSession implements Serializable {

    @TableId(value = "session_id", type = IdType.INPUT)
    private String sessionId;

    @TableField("session_data")
    private byte[] sessionData;
}
