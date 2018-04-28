package cn.wodesh.dao.sql;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;

import java.lang.reflect.Field;

/**
 * Created by TS on 2018/2/25.
 */
public class TemplateSQL {

    /**
     * 添加数据sql
     * @param t
     * @param <T>
     * @return
     */
    public static  <T> String save(T t){
        StringBuffer sb = new StringBuffer();
        StringBuffer sbv = new StringBuffer();
        sb.append("insert into ");
        Class clazz = t.getClass();
        sb.append(tableName(clazz));
        sb.append(" (");
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields){
            boolean fieldAnn = f.isAnnotationPresent(Column.class);
            boolean idAnn = f.isAnnotationPresent(ID.class);
            if(idAnn) {
                ID id = f.getAnnotation(ID.class);
                if (id.increment())
                    continue;
            }
            String name = null;
            if(fieldAnn) {
                Column column = f.getAnnotation(Column.class);
                String n = column.name();
                name = !"default".equals(n) ? n : f.getName();
            }else {
                continue;
            }
            sb.append(name);
            sb.append(",");
            sbv.append("#{");
            sbv.append(f.getName());
            sbv.append("},");
        }
        String sqlstart = sb.substring(0 , sb.length() - 1);
        sb = new StringBuffer();
        sb.append(sqlstart);
        sb.append(") values (");
        sb.append(sbv.substring(0 , sbv.length() - 1));
        sb.append(")");
        return sb.toString();
    }

    /**
     * 按id删除
     * @param id
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String deleteById(Object id , Class<T> c){
        StringBuffer sb = new StringBuffer();
        Field f = fieldId(c);
        String name = f.getAnnotation(Column.class).name();
        String idName = !"default".equals(name) ? name : f.getName();
        sb.append("delete from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(idName);
        sb.append(" = '");
        sb.append(id);
        sb.append("'");
        return sb.toString();
    }

    /**
     * 按条件 删除
     * @param sql
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String deleteBySQLRequire(String sql , Class<T> c){
        StringBuffer sb = new StringBuffer();
        sb.append("delete from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(sql);
        return sb.toString();
    }

    /**
     * 根据id查询
     * @param id
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String findById(Object id , Class<T> c){
        StringBuffer sb = new StringBuffer();
        Field fs = fieldId(c);
        String name = fs.getAnnotation(Column.class).name();
        String idName = !"default".equals(name) ? name : fs.getName();
        sb.append("select ");
        sb.append(propertyForColumn(c));
        sb.append(" from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(idName);
        sb.append(" = '");
        sb.append(id);
        sb.append("'");
        return sb.toString();
    }

    /**
     * 根据sql条件查询对象
     * @param sql
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String findBySQLRequire(String sql , Class<T> c){
        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append(propertyForColumn(c));
        sb.append(" from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(sql);
        return sb.toString();
    }

    /**
     * 根据sql条件查询对象
     * @param sql
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String findBySQLRequireToNumber(String sql , Class<T> c){
        StringBuffer sb = new StringBuffer();
        sb.append("select count(1) from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(sql);
        return sb.toString();
    }



    /**
     * 查询对象集合
     * @param c
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String findByList(Class<T> c){
        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append(propertyForColumn(c));
        sb.append(" from ");
        sb.append(tableName(c));
        return sb.toString();
    }

    /**
     * 根据对象修改 参数
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> String updateById(T t) throws Exception {
        String id = null, name = null;
        Object idval = null;
        Class c = t.getClass();
        StringBuffer sb = new StringBuffer();
        StringBuffer sbv = new StringBuffer();
        sb.append("update ");
        sb.append(tableName(c));
        sb.append(" set ");
        Field [] fed = c.getDeclaredFields();
        for(Field f : fed){
            boolean boid = f.isAnnotationPresent(ID.class);
            boolean fieldbo= f.isAnnotationPresent(Column.class);
            f.setAccessible(true);
            if(boid){
                if(!fieldbo)
                    throw new NullPointerException("实体类"
                            + c.getSimpleName() + "字段"+ f.getName() +"未定义为数据库对相应字段");
                name = f.getAnnotation(Column.class).name();
                id = "default".equals(name) ? f.getName() : name;
                idval = f.get(t);
                continue;
            }
            if(fieldbo){
                Object o = f.get(t);
                if (o == null)
                    continue;
                name = f.getAnnotation(Column.class).name();
                sbv.append("default".equals(name) ? f.getName() : name);
                sbv.append("='");
                sbv.append(o);
                sbv.append("',");
            }
        }
        String sql = sb.toString();
        sb = new StringBuffer();
        sb.append(sql);
        sb.append(sbv.substring(0 , sbv.length() - 1));
        sb.append(" where ");
        sb.append(id);
        sb.append(" = '");
        sb.append(idval);
        sb.append("'");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 根据sql条件修改对象
     * @param sql
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String updateBySQLRequire(String sql , Class<T> c){
        StringBuffer sb = new StringBuffer();
        sb.append("update ");
        sb.append(tableName(c));
        sb.append(" set ");
        sb.append(sql);
        return sb.toString();
    }

    /**
     * 原生sql
     * @param sql
     * @return
     */
    public static String bySQL(String sql){
        return sql;
    }

    /**
     * 返回表名
     * @param c
     * @return
     */
    public static String tableName(Class c){
        boolean tableAnn = c.isAnnotationPresent(TableName.class);
        return tableAnn ?
                ((TableName) c.getAnnotation(TableName.class)).name() :
                c.getSimpleName();
    }


    public static Field fieldId(Class c){
        Field[] f = c.getDeclaredFields();
        int count = 0;
        for(Field s : f){
            boolean b = s.isAnnotationPresent(ID.class);
            if(b){
                if(!s.isAnnotationPresent(Column.class))
                    throw new NullPointerException("实体类"
                            + c.getSimpleName() + "字段"+ s.getName() +"未定义为数据库对相应字段");
                return s;
            }
            count++;
        }
        if(count == f.length){
            throw new NullPointerException("实体类" + c.getSimpleName() + "未定义ID注解");
        }
        return null;
    }

    /**
     * 查询返回 字段映射
     * @param c
     * @return
     */
    public static String propertyForColumn(Class c){
        StringBuffer sbv = new StringBuffer();
        Field[] fields = c.getDeclaredFields();
        for(Field f : fields){
            boolean fieldAnn = f.isAnnotationPresent(Column.class);
            if(fieldAnn){
                String na = f.getAnnotation(Column.class).name();
                if("default".equals(na)){
                    sbv.append(f.getName()).append(",");
                }else {
                    sbv.append(na).append(" ").append(f.getName()).append(",");
                }
            }
        }
        return sbv.substring(0 , sbv.length() - 1);
    }
}
