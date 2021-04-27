package org.lyh.multidatabase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class APIMgrSysinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public APIMgrSysinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRowGuidIsNull() {
            addCriterion("row_guid is null");
            return (Criteria) this;
        }

        public Criteria andRowGuidIsNotNull() {
            addCriterion("row_guid is not null");
            return (Criteria) this;
        }

        public Criteria andRowGuidEqualTo(String value) {
            addCriterion("row_guid =", value, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidNotEqualTo(String value) {
            addCriterion("row_guid <>", value, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidGreaterThan(String value) {
            addCriterion("row_guid >", value, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidGreaterThanOrEqualTo(String value) {
            addCriterion("row_guid >=", value, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidLessThan(String value) {
            addCriterion("row_guid <", value, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidLessThanOrEqualTo(String value) {
            addCriterion("row_guid <=", value, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidLike(String value) {
            addCriterion("row_guid like", value, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidNotLike(String value) {
            addCriterion("row_guid not like", value, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidIn(List<String> values) {
            addCriterion("row_guid in", values, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidNotIn(List<String> values) {
            addCriterion("row_guid not in", values, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidBetween(String value1, String value2) {
            addCriterion("row_guid between", value1, value2, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andRowGuidNotBetween(String value1, String value2) {
            addCriterion("row_guid not between", value1, value2, "rowGuid");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andSysCodeIsNull() {
            addCriterion("sys_code is null");
            return (Criteria) this;
        }

        public Criteria andSysCodeIsNotNull() {
            addCriterion("sys_code is not null");
            return (Criteria) this;
        }

        public Criteria andSysCodeEqualTo(String value) {
            addCriterion("sys_code =", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotEqualTo(String value) {
            addCriterion("sys_code <>", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeGreaterThan(String value) {
            addCriterion("sys_code >", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_code >=", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLessThan(String value) {
            addCriterion("sys_code <", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLessThanOrEqualTo(String value) {
            addCriterion("sys_code <=", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLike(String value) {
            addCriterion("sys_code like", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotLike(String value) {
            addCriterion("sys_code not like", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeIn(List<String> values) {
            addCriterion("sys_code in", values, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotIn(List<String> values) {
            addCriterion("sys_code not in", values, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeBetween(String value1, String value2) {
            addCriterion("sys_code between", value1, value2, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotBetween(String value1, String value2) {
            addCriterion("sys_code not between", value1, value2, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysDescIsNull() {
            addCriterion("sys_desc is null");
            return (Criteria) this;
        }

        public Criteria andSysDescIsNotNull() {
            addCriterion("sys_desc is not null");
            return (Criteria) this;
        }

        public Criteria andSysDescEqualTo(String value) {
            addCriterion("sys_desc =", value, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescNotEqualTo(String value) {
            addCriterion("sys_desc <>", value, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescGreaterThan(String value) {
            addCriterion("sys_desc >", value, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescGreaterThanOrEqualTo(String value) {
            addCriterion("sys_desc >=", value, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescLessThan(String value) {
            addCriterion("sys_desc <", value, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescLessThanOrEqualTo(String value) {
            addCriterion("sys_desc <=", value, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescLike(String value) {
            addCriterion("sys_desc like", value, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescNotLike(String value) {
            addCriterion("sys_desc not like", value, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescIn(List<String> values) {
            addCriterion("sys_desc in", values, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescNotIn(List<String> values) {
            addCriterion("sys_desc not in", values, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescBetween(String value1, String value2) {
            addCriterion("sys_desc between", value1, value2, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysDescNotBetween(String value1, String value2) {
            addCriterion("sys_desc not between", value1, value2, "sysDesc");
            return (Criteria) this;
        }

        public Criteria andSysNameIsNull() {
            addCriterion("sys_name is null");
            return (Criteria) this;
        }

        public Criteria andSysNameIsNotNull() {
            addCriterion("sys_name is not null");
            return (Criteria) this;
        }

        public Criteria andSysNameEqualTo(String value) {
            addCriterion("sys_name =", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotEqualTo(String value) {
            addCriterion("sys_name <>", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameGreaterThan(String value) {
            addCriterion("sys_name >", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_name >=", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLessThan(String value) {
            addCriterion("sys_name <", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLessThanOrEqualTo(String value) {
            addCriterion("sys_name <=", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLike(String value) {
            addCriterion("sys_name like", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotLike(String value) {
            addCriterion("sys_name not like", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameIn(List<String> values) {
            addCriterion("sys_name in", values, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotIn(List<String> values) {
            addCriterion("sys_name not in", values, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameBetween(String value1, String value2) {
            addCriterion("sys_name between", value1, value2, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotBetween(String value1, String value2) {
            addCriterion("sys_name not between", value1, value2, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysUpwdIsNull() {
            addCriterion("sys_upwd is null");
            return (Criteria) this;
        }

        public Criteria andSysUpwdIsNotNull() {
            addCriterion("sys_upwd is not null");
            return (Criteria) this;
        }

        public Criteria andSysUpwdEqualTo(String value) {
            addCriterion("sys_upwd =", value, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdNotEqualTo(String value) {
            addCriterion("sys_upwd <>", value, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdGreaterThan(String value) {
            addCriterion("sys_upwd >", value, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdGreaterThanOrEqualTo(String value) {
            addCriterion("sys_upwd >=", value, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdLessThan(String value) {
            addCriterion("sys_upwd <", value, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdLessThanOrEqualTo(String value) {
            addCriterion("sys_upwd <=", value, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdLike(String value) {
            addCriterion("sys_upwd like", value, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdNotLike(String value) {
            addCriterion("sys_upwd not like", value, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdIn(List<String> values) {
            addCriterion("sys_upwd in", values, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdNotIn(List<String> values) {
            addCriterion("sys_upwd not in", values, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdBetween(String value1, String value2) {
            addCriterion("sys_upwd between", value1, value2, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUpwdNotBetween(String value1, String value2) {
            addCriterion("sys_upwd not between", value1, value2, "sysUpwd");
            return (Criteria) this;
        }

        public Criteria andSysUnameIsNull() {
            addCriterion("sys_uname is null");
            return (Criteria) this;
        }

        public Criteria andSysUnameIsNotNull() {
            addCriterion("sys_uname is not null");
            return (Criteria) this;
        }

        public Criteria andSysUnameEqualTo(String value) {
            addCriterion("sys_uname =", value, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameNotEqualTo(String value) {
            addCriterion("sys_uname <>", value, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameGreaterThan(String value) {
            addCriterion("sys_uname >", value, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_uname >=", value, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameLessThan(String value) {
            addCriterion("sys_uname <", value, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameLessThanOrEqualTo(String value) {
            addCriterion("sys_uname <=", value, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameLike(String value) {
            addCriterion("sys_uname like", value, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameNotLike(String value) {
            addCriterion("sys_uname not like", value, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameIn(List<String> values) {
            addCriterion("sys_uname in", values, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameNotIn(List<String> values) {
            addCriterion("sys_uname not in", values, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameBetween(String value1, String value2) {
            addCriterion("sys_uname between", value1, value2, "sysUname");
            return (Criteria) this;
        }

        public Criteria andSysUnameNotBetween(String value1, String value2) {
            addCriterion("sys_uname not between", value1, value2, "sysUname");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(String value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(String value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(String value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(String value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(String value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLike(String value) {
            addCriterion("role_id like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotLike(String value) {
            addCriterion("role_id not like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<String> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<String> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(String value1, String value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(String value1, String value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andSysSaltIsNull() {
            addCriterion("sys_salt is null");
            return (Criteria) this;
        }

        public Criteria andSysSaltIsNotNull() {
            addCriterion("sys_salt is not null");
            return (Criteria) this;
        }

        public Criteria andSysSaltEqualTo(String value) {
            addCriterion("sys_salt =", value, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltNotEqualTo(String value) {
            addCriterion("sys_salt <>", value, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltGreaterThan(String value) {
            addCriterion("sys_salt >", value, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltGreaterThanOrEqualTo(String value) {
            addCriterion("sys_salt >=", value, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltLessThan(String value) {
            addCriterion("sys_salt <", value, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltLessThanOrEqualTo(String value) {
            addCriterion("sys_salt <=", value, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltLike(String value) {
            addCriterion("sys_salt like", value, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltNotLike(String value) {
            addCriterion("sys_salt not like", value, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltIn(List<String> values) {
            addCriterion("sys_salt in", values, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltNotIn(List<String> values) {
            addCriterion("sys_salt not in", values, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltBetween(String value1, String value2) {
            addCriterion("sys_salt between", value1, value2, "sysSalt");
            return (Criteria) this;
        }

        public Criteria andSysSaltNotBetween(String value1, String value2) {
            addCriterion("sys_salt not between", value1, value2, "sysSalt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}