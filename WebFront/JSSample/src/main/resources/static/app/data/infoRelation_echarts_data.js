legendData = [ {
	name : '人员',
	icon : 'rect' //'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
}, {
	name : '企业',
	icon : 'roundRect'
}, {
	name : '业务类型',
	icon : 'circle'
}, {
	name : '地区',
	icon : 'circle'
}, {
	name : '供应商',
	icon : 'roundRect'
} ];

data = [ {
	id : 0,
	category : 0,
	name : '人员1',
	symbol : 'roundRect',
	value : 20,
	symbolSize : 80
}, {
	id : 1,
	category : 1,
	name : '企业1',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 2,
	category : 1,
	name : '企业2',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 3,
	category : 1,
	name : '企业3',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 11,
	category : 0,
	name : '人员2',
	symbol : 'circle',
	value : 20,
	symbolSize : 60,
	yId : "jvm",
	host : "192.168.6.37",
	port : "7001",
	username : "weblogic",
	pwd : "weblogic1"
}, {
	id : 12,
	category : 0,
	name : '人员3',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 13,
	category : 0,
	name : '人员4',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 14,
	category : 0,
	name : '人员5',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 15,
	category : 0,
	name : '人员6',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 16,
	category : 0,
	name : '人员7',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 17,
	category : 0,
	name : '人员8',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 21,
	category : 2,
	name : '业务类型1',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 22,
	category : 2,
	name : '业务类型2',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 23,
	category : 2,
	name : '业务类型3',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 31,
	category : 3,
	name : '地区1',
	symbol : 'circle',
	value : 20,
	symbolSize : 70
}, {
	id : 32,
	category : 3,
	name : '地区2',
	symbol : 'circle',
	value : 20,
	symbolSize : 70
}, {
	id : 33,
	category : 3,
	name : '地区3',
	symbol : 'circle',
	value : 20,
	symbolSize : 70
}, {
	id : 41,
	category : 4,
	name : '供应商1',
	symbol : 'circle',
	value : 20,
	symbolSize : 70,
	yId : "jdbc",
	port : "1521",
	host : "192.168.11.11",
	username : "222222222",
	pwd : "11111111",
	instance : "orcl"
}, {
	id : 42,
	category : 4,
	name : '供应商2',
	symbol : 'circle',
	value : 20,
	symbolSize : 70,
	yId : "jdbc",
	port : "1521",
	host : "192.168.11.11",
	username : "222222222",
	pwd : "11111111",
	instance : "orcl"
}, {
	id : 43,
	category : 4,
	name : '供应商3',
	symbol : 'circle',
	value : 20,
	symbolSize : 70,
	yId : "jdbc",
	port : "1521",
	host : "192.168.11.11",
	username : "222222222",
	pwd : "11111111",
	instance : "orcl"
} ]

//symbol name：用于和 legend 对应以及格式化 tooltip 的内容。 label有效
var categoriesData = [
	{
		name : '人员',
		symbol : 'rect',
		label : { //标签样式
		}
	}, {
		name : '企业',
		symbol : 'rect'
	}, {
		name : '业务类型',
		symbol : 'roundRect'
	}, {
		name : '地区',
		symbol : 'roundRect'
	}, {
		name : '供应商',
		symbol : 'roundRect'
	} ];

//edges是其别名代表节点间的关系数据。
var linksData = [
	{
		source : 1,
		target : 0
	}, {
		source : 2,
		target : 0
	}, {
		source : 3,
		target : 0
	}, {
		source : 4,
		target : 1
	}, {
		source : 5,
		target : 1
	}, {
		source : 6,
		target : 1
	}, {
		source : 7,
		target : 2
	}, {
		source : 8,
		target : 2
	}, {
		source : 9,
		target : 3
	}, {
		source : 10,
		target : 3
	}, {
		source : 11,
		target : 1
	}, {
		source : 12,
		target : 2
	}, {
		source : 13,
		target : 3
	}, {
		source : 14,
		target : 1
	}, {
		source : 15,
		target : 2
	}, {
		source : 16,
		target : 3
	}, {
		source : 17,
		target : 1
	}, {
		source : 18,
		target : 2
	}, {
		source : 19,
		target : 3
	}
 ];