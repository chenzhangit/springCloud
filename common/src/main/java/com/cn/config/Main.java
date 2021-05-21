package com.cn.config;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    // mysql驱动包名
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    // 数据库连接地址
    private static final String URL = "jdbc:mysql://172.16.10.248:3306/vebsp_11?useUnicode=true&characterEncoding=UTF-8&&useSSL=false";
    // 用户名
    private static final String USER_NAME = "root";
    // 密码
    private static final String PASSWORD = "mingkemysql..";
    //
    private static PreparedStatement prst = null;

    private static ResultSet rs = null;

    private static Connection connection = null;

    // mysql驱动包名
//    private static final String DRIVER_NAME1 = "com.mysql.jdbc.Driver";
    // 数据库连接地址
    private static final String URL1 = "jdbc:mysql://172.161.10.248:3306/vebsp_11?useUnicode=true&characterEncoding=UTF-8&&useSSL=false";
    // 用户名
    private static final String USER_NAME1 = "root";
    // 密码
    private static final String PASSWORD1 = "mingkemysql..";
    //
    private static PreparedStatement prst1 = null;

    private static ResultSet rs1 = null;

    private static Connection connection1 = null;

    public static void main(String[] args){
//        Connection connection = null;
        try {
            //加载mysql的驱动类
            Class.forName(DRIVER_NAME);
            //获取数据库连接
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            //获取数据库连接
            connection1 = DriverManager.getConnection(URL1, USER_NAME1, PASSWORD1);

            // 原有的专业ID
//            int professionId = 194;

            // 要增加的专业ID
//            int insertProfessionId = 260;

            // 原有的数据
//            List<Map<String, Object>> questionnaireList = new ArrayList<>();
//            List<Map<String, Object>> surveyList =  new ArrayList<>();
            List<Map<String, Object>> populationList = new ArrayList<>();
            List<Map<String, Object>> answerList = new ArrayList<>();

            // 增加的数据
//            List<Map<String, Object>> insertQuestionnaireList = new ArrayList<>();
//            List<Map<String, Object>> insertSurveyList =  new ArrayList<>();
            List<Map<String, Object>> insertPopulationList = new ArrayList<>();
            List<Map<String, Object>> insertAnswerList = new ArrayList<>();

            /*//mysql查询语句
            String questionnaireSql = "SELECT * FROM tb_questionnaire WHERE professionId = " + professionId + " AND enable = 1 ORDER BY id";

            // 问卷
            questionnaireList = resultSet(connection, questionnaireSql, questionnaireList);

            String surveySql = "SELECT * FROM tb_questionnaire_survey WHERE professionId = " + professionId + " ORDER BY id";
            // 问卷任务
            surveyList = resultSet(connection, surveySql, surveyList);


*/



            // 创建事务 开启手动提交
            connection1.setAutoCommit(false);

            Map<String, Object> param = null;
            /*// 增加问卷
            StringBuffer insertQuestionnaireSql;
            if (questionnaireList == null || surveyList == null || questionnaireList.size() == 0 || surveyList.size() == 0) {
                return;
            }
            for (Map<String, Object> map : questionnaireList) {
                insertQuestionnaireSql = new StringBuffer();
                insertQuestionnaireSql.append("INSERT INTO tb_questionnaire(name, schoolId, professionId, createTime, updateTime, questionnaireObjectId, title, description, creator, updater, enable, hasAddToTemplate) ");
                insertQuestionnaireSql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                prst = connection.prepareStatement(insertQuestionnaireSql.toString(), Statement.RETURN_GENERATED_KEYS);
                prst.setString(1, map.get("name") == null ? null : map.get("name").toString());
                if (map.get("schoolId") == null) {
                    prst.setNull(2, Types.INTEGER);
                } else {
                    prst.setInt(2,  Integer.parseInt(map.get("schoolId").toString()));
                }
                prst.setInt(3, insertProfessionId);
                prst.setString(4, map.get("createTime") == null ? null : map.get("createTime").toString());
                prst.setString(5, map.get("updateTime") == null ? null : map.get("updateTime").toString());
                if (map.get("questionnaireObjectId") == null) {
                    prst.setNull(6, Types.INTEGER);
                } else {
                    prst.setInt(6, Integer.parseInt(map.get("questionnaireObjectId").toString()));
                }
                prst.setString(7, map.get("title") == null ? null : map.get("title").toString());
                prst.setString(8, map.get("description") == null ? null : map.get("description").toString());
                prst.setString(9, map.get("creator") == null ? null : map.get("creator").toString());
                prst.setString(10, map.get("updater") == null ? null : map.get("updater").toString());
                if (map.get("enable") == null) {
                    prst.setNull(11, Types.INTEGER);
                } else {
                    prst.setInt(11, Integer.parseInt(map.get("enable").toString()));
                }
                if (map.get("hasAddToTemplate") == null) {
                    prst.setNull(12, Types.INTEGER);
                } else {
                    prst.setInt(12, Integer.parseInt(map.get("hasAddToTemplate").toString()));
                }
                // 发送参数，执行sql
                prst.executeUpdate();
                // 执行完上面的更新数据操作后，获取自增长列
                rs = prst.getGeneratedKeys();
                // 获取数据对应的自增长列的值
                if (rs.next()) {
                    param = new HashMap<>();
                    param.put("oldId", map.get("id"));
                    param.put("insertId", rs.getInt(1));
                    insertQuestionnaireList.add(param);
                }
            }
            close(null, prst, rs);
            // 增加问卷任务
            StringBuffer insertSurveySql;
            for (Map<String, Object> map : surveyList) {
                insertSurveySql = new StringBuffer();
                insertSurveySql.append("INSERT INTO tb_questionnaire_survey(name, schoolId, professionId, questionnaireId, questionnaireSurveyRewardId, accessCode, surveyStartTime, surveyEndTime, allSurveyMoney, surveyMoney, surveyMoneyBalance, sendFormId, releaseFormId, createTime, updateTime, status, needSendTotalCount, alreadyReceivedCount, effectiveCount, invalidCount, pattern, randomPattern, minRate, maxRate) ");
                insertSurveySql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                prst = connection.prepareStatement(insertSurveySql.toString(), Statement.RETURN_GENERATED_KEYS);

                prst.setString(1, map.get("name") == null ? null : map.get("name").toString());
                if (map.get("schoolId") == null) {
                    prst.setNull(2, Types.INTEGER);
                } else {
                    prst.setInt(2, Integer.parseInt(map.get("schoolId").toString()));
                }
                prst.setInt(3, insertProfessionId);
                if (map.get("questionnaireId") == null) {
                    prst.setNull(4, Types.INTEGER);
                } else {
                    prst.setInt(4, Integer.parseInt(map.get("questionnaireId").toString()));
                }
                if (map.get("questionnaireSurveyRewardId") == null) {
                    prst.setNull(5, Types.INTEGER);
                } else {
                    prst.setInt(5, Integer.parseInt(map.get("questionnaireSurveyRewardId").toString()));
                }
                prst.setString(6, map.get("accessCode") == null ? null : map.get("accessCode").toString());
                prst.setString(7, map.get("surveyStartTime") == null ? null : map.get("surveyStartTime").toString());
                prst.setString(8, map.get("surveyEndTime") == null ? null : map.get("surveyEndTime").toString());
                if (map.get("allSurveyMoney") == null) {
                    prst.setNull(9, Types.DECIMAL);
                } else {
                    prst.setBigDecimal(9, BigDecimal.valueOf(Double.parseDouble(map.get("allSurveyMoney").toString())));
                }
                if (map.get("surveyMoney") == null) {
                    prst.setNull(10, Types.DECIMAL);
                } else {
                    prst.setBigDecimal(10, BigDecimal.valueOf(Double.parseDouble(map.get("surveyMoney").toString())));
                }
                if (map.get("surveyMoneyBalance") == null) {
                    prst.setNull(11, Types.DECIMAL);
                } else {
                    prst.setBigDecimal(11, BigDecimal.valueOf(Double.parseDouble(map.get("surveyMoneyBalance").toString())));
                }
                if (map.get("sendFormId") == null) {
                    prst.setNull(12, Types.INTEGER);
                } else {
                    prst.setInt(12, Integer.parseInt(map.get("sendFormId").toString()));
                }
                if (map.get("releaseFormId") == null) {
                    prst.setNull(13, Types.INTEGER);
                } else {
                    prst.setInt(13, Integer.parseInt(map.get("releaseFormId").toString()));
                }
                prst.setString(14, map.get("createTime") == null ? null : map.get("createTime").toString());
                prst.setString(15, map.get("updateTime") == null ? null : map.get("updateTime").toString());
                if (map.get("status") == null) {
                    prst.setNull(16, Types.INTEGER);
                } else {
                    prst.setInt(16, Integer.parseInt(map.get("status").toString()));
                }
                if (map.get("needSendTotalCount") == null) {
                    prst.setNull(17, Types.INTEGER);
                } else {
                    prst.setInt(17, Integer.parseInt(map.get("needSendTotalCount").toString()));
                }
                if (map.get("alreadyReceivedCount") == null) {
                    prst.setNull(18, Types.INTEGER);
                } else {
                    prst.setInt(18, Integer.parseInt(map.get("alreadyReceivedCount").toString()));
                }
                if (map.get("effectiveCount") == null) {
                    prst.setNull(19, Types.INTEGER);
                } else {
                    prst.setInt(19, Integer.parseInt(map.get("effectiveCount").toString()));
                }
                if (map.get("invalidCount") == null) {
                    prst.setNull(20, Types.INTEGER);
                } else {
                    prst.setInt(20, Integer.parseInt(map.get("invalidCount").toString()));
                }
                if (map.get("pattern") == null) {
                    prst.setNull(21, Types.INTEGER);
                } else {
                    prst.setInt(21, Integer.parseInt(map.get("pattern").toString()));
                }
                if (map.get("randomPattern") == null) {
                    prst.setNull(22, Types.INTEGER);
                } else {
                    prst.setInt(22, Integer.parseInt(map.get("randomPattern").toString()));
                }
                if (map.get("minRate") == null) {
                    prst.setNull(23, Types.FLOAT);
                } else {
                    prst.setFloat(23, Float.parseFloat(map.get("minRate").toString()));
                }
                if (map.get("maxRate") == null) {
                    prst.setNull(24, Types.FLOAT);
                } else {
                    prst.setFloat(24, Float.parseFloat(map.get("maxRate").toString()));
                }
                // 发送参数，执行sql
                prst.executeUpdate();
                // 执行完上面的更新数据操作后，获取自增长列
                rs = prst.getGeneratedKeys();
                // 获取数据对应的自增长列的值
                if (rs.next()) {
                    param = new HashMap<>();
                    param.put("oldId", map.get("id"));
                    param.put("insertId", rs.getInt(1));
                    insertSurveyList.add(param);
                }

            }
            close(null, prst, rs);*/

            // 查询问卷回收样本
            StringBuffer populationSql = new StringBuffer();
            populationSql.append("SELECT * FROM tb_questionnaire_release_population ");
            populationSql.append(" WHERE questionnaireId IN(3148) ");
            populationSql.append(" AND questionnaireSurveyId IN(3050) ");
            populationSql.append(" ORDER BY id");
            populationList = resultSet(connection, populationSql.toString(), populationList);

            if (populationList == null || populationList.size() == 0) {
                return;
            }
            close(null, prst, rs);
            // 增加问卷回收样本
            StringBuffer insertPopulationSql;
            // 临时list
            List<Map<String, Object>> temporarys;
            for (Map<String, Object> map : populationList) {
                insertPopulationSql = new StringBuffer();
                insertPopulationSql.append("INSERT INTO tb_questionnaire_release_population(userId, contactsId, questionnaireSurveyId, questionnaireId, recycleStatus, createTime, recycleTime, isPush, username, mobile, email, device, fwhOpenId, accessCode, rewardMoney, rewardStatus) ");
                insertPopulationSql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                prst = connection1.prepareStatement(insertPopulationSql.toString(), Statement.RETURN_GENERATED_KEYS);
                if (map.get("userId") == null) {
                    prst.setNull(1, Types.INTEGER);
                } else {
                    prst.setInt(1, Integer.parseInt(map.get("userId").toString()));
                }
                if (map.get("contactsId") == null) {
                    prst.setNull(2, Types.INTEGER);
                } else {
                    prst.setInt(2, Integer.parseInt(map.get("contactsId").toString()));
                }
                prst.setInt(3, 3050);
                prst.setInt(4, 3148);
                if (map.get("recycleStatus") == null) {
                    prst.setNull(5, Types.INTEGER);
                } else {
                    prst.setInt(5, Integer.parseInt(map.get("recycleStatus").toString()));
                }
                prst.setString(6, map.get("createTime") == null ? null : map.get("createTime").toString());
                prst.setString(7, map.get("recycleTime") == null ? null : map.get("recycleTime").toString());
                if (map.get("isPush") == null) {
                    prst.setNull(8, Types.INTEGER);
                } else {
                    prst.setInt(8, Integer.parseInt(map.get("isPush").toString()));
                }
                prst.setString(9, map.get("username") == null ? null : map.get("username").toString());
                prst.setString(10, map.get("mobile") == null ? null : map.get("mobile").toString());
                prst.setString(11, map.get("email") == null ? null : map.get("email").toString());
                if (map.get("isPush") == null) {
                    prst.setNull(12, Types.INTEGER);
                } else {
                    prst.setInt(12, Integer.parseInt(map.get("isPush").toString()));
                }
                prst.setString(13, map.get("fwhOpenId") == null ? null : map.get("fwhOpenId").toString());
                prst.setString(14, map.get("accessCode") == null ? null : map.get("accessCode").toString());
                if (map.get("rewardMoney") == null) {
                    prst.setNull(15, Types.DECIMAL);
                } else {
                    prst.setBigDecimal(15, BigDecimal.valueOf(Double.parseDouble(map.get("rewardMoney").toString())));
                }
                if (map.get("rewardStatus") == null) {
                    prst.setNull(16, Types.INTEGER);
                } else {
                    prst.setInt(16, Integer.parseInt(map.get("rewardStatus").toString()));
                }
                // 发送参数，执行sql
                prst.executeUpdate();
                // 执行完上面的更新数据操作后，获取自增长列
                rs = prst.getGeneratedKeys();
                // 获取数据对应的自增长列的值
                if (rs.next()) {
                    param = new HashMap<>();
                    param.put("oldId", map.get("id"));
                    param.put("insertId", rs.getInt(1));
                    insertPopulationList.add(param);
                }
            }




            // 查询问卷答案记录
            StringBuffer answerSql = new StringBuffer();
            answerSql.append("SELECT * FROM tb_questionnaire_question_answer ");
            if (populationList.size() > 0) {
                answerSql.append(" WHERE questionnaireReleasePopulationId IN( ");
                for (Map<String, Object> map: populationList) {
                    answerSql.append(map.get("id").toString() + ",");
                }
                answerSql = new StringBuffer(answerSql.substring(0, answerSql.length() - 1));
                answerSql.append(" ) ");
            }
            answerSql.append(" ORDER BY id");
            answerList = resultSet(connection, answerSql.toString(), answerList);

            if (answerList == null || answerList.size() == 0) {
                return;
            }
            // 增加问卷答案记录
            StringBuffer insertAnswerSql;
            for (Map<String, Object> map : answerList) {
                insertAnswerSql = new StringBuffer();
                insertAnswerSql.append("INSERT INTO tb_questionnaire_question_answer(questionnaireReleasePopulationId, questionnaireSurveyId, questionnaireId, questionId, subQuestionId, questionOptionId, answerContent, userId, roleId, fillTime, status, enable) ");
                insertAnswerSql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                prst = connection1.prepareStatement(insertAnswerSql.toString(), Statement.RETURN_GENERATED_KEYS);
                String populationId = map.get("questionnaireReleasePopulationId") == null ? "" : map.get("questionnaireReleasePopulationId").toString();
                int insertPopulationId;
                temporarys = insertPopulationList.stream().filter(survey -> populationId.equalsIgnoreCase(survey.get("oldId").toString())).collect(Collectors.toList());
                if (temporarys != null && temporarys.size() != 0) {
                    insertPopulationId = Integer.parseInt(temporarys.get(0).get("insertId").toString());
                    prst.setInt(1, insertPopulationId);
                } else {
                    prst.setNull(1, Types.INTEGER);
                }
                prst.setInt(2, 3050);
                prst.setInt(3, 3148);
                if (map.get("questionId") == null) {
                    prst.setNull(4, Types.INTEGER);
                } else {
                    prst.setInt(4, Integer.parseInt(map.get("questionId").toString()));
                }
                if (map.get("subQuestionId") == null) {
                    prst.setNull(5, Types.INTEGER);
                } else {
                    prst.setInt(5, Integer.parseInt(map.get("subQuestionId").toString()));
                }
                if (map.get("questionOptionId") == null) {
                    prst.setNull(6, Types.INTEGER);
                } else {
                    prst.setInt(6, Integer.parseInt(map.get("questionOptionId").toString()));
                }
                prst.setString(7, map.get("answerContent") == null ? null : map.get("answerContent").toString());
                if (map.get("userId") == null) {
                    prst.setNull(8, Types.INTEGER);
                } else {
                    prst.setInt(8, Integer.parseInt(map.get("userId").toString()));
                }
                if (map.get("roleId") == null) {
                    prst.setNull(9, Types.INTEGER);
                } else {
                    prst.setInt(9, Integer.parseInt(map.get("roleId").toString()));
                }
                prst.setString(10, map.get("fillTime") == null ? null : map.get("fillTime").toString());
                if (map.get("status") == null) {
                    prst.setNull(11, Types.INTEGER);
                } else {
                    prst.setInt(11, Integer.parseInt(map.get("status").toString()));
                }
                if (map.get("enable") == null) {
                    prst.setNull(12, Types.INTEGER);
                } else {
                    prst.setInt(12, Integer.parseInt(map.get("enable").toString()));
                }
                // 发送参数，执行sql
                prst.executeUpdate();
                // 执行完上面的更新数据操作后，获取自增长列
                rs = prst.getGeneratedKeys();
                // 获取数据对应的自增长列的值
                if (rs.next()) {
                    param = new HashMap<>();
                    param.put("oldId", map.get("id"));
                    param.put("insertId", rs.getInt(1));
                    insertAnswerList.add(param);
                }
            }

            // 提交事务
            connection1.commit();
//            connection1.rollback();

            System.out.println("增加成功！");
            System.out.println();
//            System.out.println("questionnaireList=" + questionnaireList.size());
//            System.out.println("surveyList=" + surveyList.size());
            System.out.println("populationList=" + populationList);
//            System.out.println("answerList=" + answerList);
            System.out.println("populationListsize=" + populationList.size());
            System.out.println("answerListsize=" + answerList.size());
            System.out.println();
            System.out.println();
//            System.out.println("insertQuestionnaireList=" + insertQuestionnaireList.size());
//            System.out.println("insertSurveyList=" + insertSurveyList.size());
            System.out.println("insertPopulationList=" + insertPopulationList.size());
            System.out.println("insertAnswerList=" + insertAnswerList.size());
            System.out.println();

            // questionnaireList: 5
            // surveyList: 5
            // populationList: 1100
            //  answerList: 39239

        } catch (Exception e) {
            try {
                connection1.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            close(connection1, prst, rs);
            close(connection, null, null);
        }
    }

    public static List<Map<String, Object>> resultSet(Connection connection, String sql, List<Map<String, Object>> params) throws SQLException {
        try {
            prst = connection.prepareStatement(sql);
            ResultSetMetaData rsmd = prst.getMetaData() ;
            int columnCount = rsmd.getColumnCount();
            //结果集
            rs = prst.executeQuery();
            Map<String, Object> param;
            while (rs.next()) {
                param = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    param.put(rsmd.getColumnName(i + 1), rs.getString(i + 1));
                }
                params.add(param);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, prst, rs);
        }
        return params;
    }

    public static void close(Connection connection, PreparedStatement prst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (prst != null) {
                prst.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
