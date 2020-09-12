package ${basePackage}.mapper.${table.className?lower_case};
import ${basePackage}.base.GenericMapper;
import ${basePackage}.entity.${table.className?lower_case}.${table.className};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

 /**  
* @Description: ${table.className}Mapper类
* @author ${author}
* @date ${.now}
* @version V1.0  
 */
public interface ${table.className}Mapper extends BaseMapper<${table.className}> ,GenericMapper<${table.className}>{

}
