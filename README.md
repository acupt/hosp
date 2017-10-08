# hosp
基于springboot

## 部署

+ 开启mysql，需要数据库"hosp",utf8编码
+ 开启es
+ 拷贝配置文件acupt-filter.properties到目录/home/admin/conf/下（自定义路径请修改pom.xml）
+ 根据机器环境修改配置文件
+ 在项目主目录中执行package.sh，打包成功出现target目录
+ 进入target目录，执行命令： nohup java -jar hosp-1.0-SNAPSHOT.jar &
+ 启动成功

## 关闭

+ 执行命令，找到pid（第一个数字）：ps -ef|grep hosp
+ kill pid