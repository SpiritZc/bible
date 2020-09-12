package com.moon.bible.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**  
 * @ClassName: PageEntity
 * @Description: 分页参数用基底实体类
 * @author caiyang
 * @date 2020-05-29 11:04:00 
*/  
@Data
public class PageEntity extends BaseEntity{

	/**
	 *	 每页显示的条数
	 */
	@TableField(exist = false)
	private Integer pageSize = 10;

	/**
	 * 	当前页数
	 */
	@TableField(exist = false)
	private int currentPage = 1;

	/**
	 * 	表格数据行
	 */
	@TableField(exist = false)
	private List<?> data;

	/**
	 * 总条数
	 */
	@TableField(exist = false)
	private Long total = (long) 0;
}
