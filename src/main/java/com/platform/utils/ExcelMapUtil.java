package com.platform.utils;

import java.util.LinkedHashMap;

/**
 * excel头部于对象相对应
 * 
 * @author jinlei
 *
 */
public class ExcelMapUtil {

    /**
     * 资料
     * 
     * @return map
     */
    public static LinkedHashMap<String, String> projectMap() {
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("类型", "type");
        fieldMap.put("工程名", "projectName");
        fieldMap.put("城市", "city");
        fieldMap.put("原始数据路径", "sourceUrl");
        fieldMap.put("导出成果路径", "resultUrl");
        fieldMap.put("解算进度", "resolveFlag");
        fieldMap.put("外业里程(Km)", "outMileage");
        fieldMap.put("实际里程(Km)", "factMileage");
        fieldMap.put("数据回传时间", "returnTime");
        fieldMap.put("质检提交时间", "qcEndTime");
        fieldMap.put("RECORD个数", "recordNum");
        return fieldMap;
    }

    /**
     * 资料(二期)
     * 
     * @return map
     */
    public static LinkedHashMap<String, String> projectMap2() {
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("城市", "city");
        fieldMap.put("类型", "type");
        fieldMap.put("工程名*", "projectName");
        fieldMap.put("原始数据路径*", "sourceUrl");
        fieldMap.put("导出成果路径*", "resultUrl");
        fieldMap.put("外业里程(Km)*", "outMileage");
        fieldMap.put("实际里程(Km)*", "factMileage");
        fieldMap.put("RECORD个数*", "recordNum");
        fieldMap.put("数据回传时间", "returnTime");
        fieldMap.put("解算进度", "resolveFlag");
        fieldMap.put("解算负责人*", "resolveUserName");
        fieldMap.put("轨迹解算完成时间", "trailEndTime");
        fieldMap.put("点云解算开始时间", "pointCloudStartTime");
        fieldMap.put("点云解算结束时间", "pointCloudEndTime");
        fieldMap.put("解算机器", "solutionMachine");
        fieldMap.put("解算方式", "resolveMethod");
        fieldMap.put("质检开始时间", "qcStartTime");
        fieldMap.put("质检状态*", "qcStatus");
        fieldMap.put("质检提交时间", "qcEndTime");
        fieldMap.put("质检负责人*", "qcUserName");
        fieldMap.put("提交内业时间*", "indoorworkCommitTime");
        fieldMap.put("备注", "remark");
        return fieldMap;
    }

    /**
     * 资料
     * 
     * @return map
     */
    public static LinkedHashMap<String, String> jobMap() {
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("任务创建日期", "createTime");
        fieldMap.put("任务名称", "taskName");
        fieldMap.put("任务编号", "procInstId");
        fieldMap.put("任务里程(m)", "size");
        fieldMap.put("作业组", "group");
        fieldMap.put("作业人", "operator");
        fieldMap.put("资料路径", "path");
        fieldMap.put("任务状态", "taskStatus");
        fieldMap.put("作业类型", "jobType");
        fieldMap.put("内检批次", "jobCommitTimes");
        fieldMap.put("质检批次", "qcCommitTimes");
        fieldMap.put("质检结果", "qcResult");
        fieldMap.put("返工说明", "comments");
        fieldMap.put("备注", "remark");
        return fieldMap;
    }

    /**
     * 报表导入
     * 
     * @return map
     */
    public static LinkedHashMap<String, String> reportMap() {
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("projectname", "projectName");
        fieldMap.put("recoder", "recoder");
        fieldMap.put("tablename", "tableName");
        fieldMap.put("tableid", "recordId");
        fieldMap.put("x", "x");
        fieldMap.put("y", "y");
        fieldMap.put("z", "z");
        fieldMap.put("issuename", "issueName");
        fieldMap.put("issueid", "issueId");
        return fieldMap;
    }

    /**
     * 质检导出
     * @return  LinkedHashMap<String, String> 表头和实体类属性对应关系
     * @return
     */
    public static LinkedHashMap<String, String> qualityMap() {
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("日期", "createTime");
        fieldMap.put("任务名称", "taskName");
        fieldMap.put("任务编号", "procInstId");
        fieldMap.put("任务里程(m)", "size");
        fieldMap.put("质检组", "qualityGroup");
        fieldMap.put("质检人", "qualityOperator");
        fieldMap.put("资料路径", "path");
        fieldMap.put("任务状态", "taskStatus");
        fieldMap.put("作业类型", "jobType");
        fieldMap.put("质检批次", "qcCommitTimes");
        fieldMap.put("质检结果", "qcResult");
        fieldMap.put("返工说明", "comments");
        fieldMap.put("备注", "remark");
        return fieldMap;
    }

    /**
     * 用户
     * 
     * @return map
     */
    public static LinkedHashMap<String, String> userMap() {
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("工号", "code");
        fieldMap.put("真实姓名", "realName");
        fieldMap.put("用户名", "name");
        fieldMap.put("人员性质", "userProperty");
        fieldMap.put("所属单位", "userDept");
        fieldMap.put("邮箱", "email");
        fieldMap.put("入职时间", "entryTime");
        return fieldMap;
    }

    /**
     * 里程趋势导出
     * @return  LinkedHashMap<String, String> 表头和实体类属性对应关系
     * @author：zhuhaojie
     * @time：2017年4月18日 上午10:01:18
     */
    public static LinkedHashMap<String, String> mileageMap() {
        LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();
        // 导出时excel标题名称与实体类属性名对应关系 其中key为excel标题 value为实体类属性名
        fildMap.put("ID", "id");
        fildMap.put("新增(KM)", "add");
        fildMap.put("更新(KM)", "update");
        fildMap.put("删除(KM)", "delete");
        return fildMap;
    }

    /**
     * 里程趋势导出
     * @return  LinkedHashMap<String, String> 表头和实体类属性对应关系
     * @author：zhuhaojie
     * @time：2017年4月18日 上午10:01:18
     */
    public static LinkedHashMap<String, String> peopleEffictMap() {
        LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();
        // 导出时excel标题名称与实体类属性名对应关系 其中key为excel标题 value为实体类属性名
        fildMap.put("姓名", "name");
        fildMap.put("作业组", "workGroup");
        fildMap.put("作业时长", "workHour");
        fildMap.put("效率(公里/人天)", "effictOne");
        fildMap.put("效率(个/人天)", "effictTwo");
        fildMap.put("有效比", "effictRatio");
        return fildMap;
    }

    /**
     * 任务质量导出
     * @return  LinkedHashMap<String, String> 表头和实体类属性对应关系
     * @author：zhuhaojie
     * @time：2017年4月18日 下午5:07:55
     */
    public static LinkedHashMap<String, String> taskQualityMap() {
        LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();
        // 导出时excel标题名称与实体类属性名对应关系 其中key为excel标题 value为实体类属性名
        fildMap.put("作业任务ID", "jobTaskId");
        fildMap.put("质检任务ID", "qualityTaskId");
        fildMap.put("作业类型", "taskType");
        fildMap.put("城市", "city");
        fildMap.put("作业组", "workGroup");
        fildMap.put("作业员", "workMember");
        fildMap.put("作业开始时间", "workBeginTime");
        fildMap.put("作业结束时间", "workEndTime");
        fildMap.put("质检组", "qcGroup");
        fildMap.put("质检员", "qcMember");
        fildMap.put("质检开始时间", "qcBeginTime");
        fildMap.put("质检完成时间", "qcEndTime");
        fildMap.put("是否合格", "isQualified");
        fildMap.put("附件", "accessoryPath");
        return fildMap;
    }

    /**
     * @return  LinkedHashMap<String, String> 表头和实体类属性对应关系
     * @author：zhuhaojie
     * @time：2017年4月19日 上午8:31:45
     */
    public static LinkedHashMap<String, String> peopleQualityMap() {
        LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();
        // 导出时excel标题名称与实体类属性名对应关系 其中key为excel标题 value为实体类属性名
        fildMap.put("姓名", "name");
        fildMap.put("作业组", "workGroup");
        fildMap.put("检测任务数", "checkTaskNumbers");
        fildMap.put("合格任务数", "qualifiedTaskNumbers");
        fildMap.put("合格率", "qualifiedRate");
        fildMap.put("检测数据量", "qualifiedRate");
        fildMap.put("错误数", "dataErrorNumbers");
        fildMap.put("正确率", "accuracy");

        return fildMap;
    }

    /**
     * 合格率
     * @return  LinkedHashMap<String, String> 表头和实体类属性对应关系
     * @author：zhuhaojie
     * @time：2017年4月19日 上午11:02:08
     */
    public static LinkedHashMap<String, String> qualifiedRateDetailMap() {
        LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();
        // 导出时excel标题名称与实体类属性名对应关系 其中key为excel标题 value为实体类属性名
        fildMap.put("任务类型", "taskType");
        fildMap.put("检测任务数", "checkTaskNumbers");
        fildMap.put("合格任务数", "qualifiedTaskNumbers");
        fildMap.put("合格率", "qualifiedRate");
        fildMap.put("环比", "loopRate");
        return fildMap;
    }

  

    /**
     * 高德 二期 组内人员详情导出
     * @return  LinkedHashMap<String, String> 表头和实体类属性对应关系
     * @author zhuhaojie
     * @time：2017年4月19日 上午10:53:04
     */
    public static LinkedHashMap<String, String> memberDetailMap() {
        LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();

        fildMap.put("姓名", "name");
        fildMap.put("作业组", "groupName");
        fildMap.put("完成工作量(KM)", "finishMiles");
        fildMap.put("检测中工作量(KM)", "checkMiles");
        fildMap.put("剩余工作量(KM)", "surplusMiles");
        fildMap.put("未完成工作量占比%", "unfinishPercent");
        return fildMap;
    }

    /**
     * 高德 二期 质检状态列表导出
     * @return  LinkedHashMap<String, String> 表头和实体类属性对应关系
     * @author zhuhaojie
     * @time：2017年4月19日 上午10:53:04
     */
    public static LinkedHashMap<String, String> qualityStatusListMap() {
        LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();
      
        fildMap.put("省份", "province");
        fildMap.put("单位", "department");
       // fildMap.put("作业组", "groupName");
        fildMap.put("质检员", "userName");
        fildMap.put("开始时间", "searchBeginTime");
        fildMap.put("结束时间", "searchEndTime");
        fildMap.put("已提交质检总任务数", "totalNumber");
        fildMap.put("质检完成(可提交编译)", "finishNumber");
        fildMap.put("质检进行中", "checkingNumber");
        fildMap.put("返回修改(1次)", "repairOne");
        fildMap.put("返回修改(2次)", "repairTwo");
        fildMap.put("返回修改(3次)", "repairThree");
        fildMap.put("图标验证中", "tagCheckingNumber");
        fildMap.put("质检未检查", "uncheckNumber");
        fildMap.put("返工", "reworkNumber");
        return fildMap;
    }
    /**
     * 高德 二期 生产和质检组内人员详情 列表导出 
     * @param type 1-生产 2-质检
     * @return
     */
	public static LinkedHashMap<String, String> prodPeopleDetailMap(String intoPage) {
		LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();
		fildMap.put("姓名", "name"); 
		fildMap.put("作业组", "taskGroup"); 
		fildMap.put("作业时长(小时)", "taskTime"); 
		fildMap.put("产出(km)", "outputMileage"); 
		fildMap.put("效率(km)", "efficency"); 
		if("prod".equals(intoPage)){
			fildMap.put("环比(%)", "rate"); 
		}
		return fildMap;
	}

	public static LinkedHashMap<String, String> iconQualitysMap(String type) {
		LinkedHashMap<String, String> fildMap = new LinkedHashMap<String, String>();
		fildMap.put("省份", "province");
		fildMap.put("单位", "company");
		fildMap.put("作业组", "groupName");
		fildMap.put("作业员", "userName");
		if("1".equals(type)){
			fildMap.put("加标正确率", "correctRate");
			fildMap.put("质检图标总数", "iconTotal");
			fildMap.put("正确图标总数", "correctIconTotal");
			fildMap.put("正确图标A", "correctIconA");
			fildMap.put("正确图标B", "correctIconB");
			fildMap.put("正确图标C", "correctIconC");
			fildMap.put("正确图标D", "correctIconD");
		}else {
			fildMap.put("修改准确率", "correctRate");
			fildMap.put("修改正确图标数量", "iconTotal");
			fildMap.put("需修改图标总数", "correctIconTotal");
			fildMap.put("需修改A", "correctIconA");
			fildMap.put("需修改B", "correctIconB");
			fildMap.put("需修改C", "correctIconC");
			fildMap.put("需修改D", "correctIconD");
		}
		return fildMap;
	}

}
