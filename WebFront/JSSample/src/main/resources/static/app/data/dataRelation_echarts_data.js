legendData = [ {
	name : '数据源',
	icon : 'roundRect' //'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
}, {
	name : '数据表',
	icon : 'rect'
}, {
	name : '属性',
	icon : 'circle'
} ];


//symbol name：用于和 legend 对应以及格式化 tooltip 的内容。 label有效
var categoriesData = [
	{
		name : '数据源',
		symbol : 'roundRect',
		label : { //标签样式
		}
	}, {
		name : '数据表',
		symbol : 'rect'
	}, {
		name : '属性',
		symbol : 'circle'
	} ];

data = [ {
	id : 0,
	category : 0,
	name : '数据源1',
	symbol : 'roundRect',
	value : 20,
	symbolSize : 80
}, {
	id : 1,
	category : 0,
	name : '数据源2',
	symbol : 'roundRect',
	value : 20,
	symbolSize : 80
}, {
	id : 2,
	category : 0,
	name : '数据源3',
	symbol : 'roundRect',
	value : 20,
	symbolSize : 80
}, {
	id : 3,
	category : 0,
	name : '数据源4',
	symbol : 'roundRect',
	value : 20,
	symbolSize : 80
}, {
	id : 4,
	category : 0,
	name : '数据源5',
	symbol : 'roundRect',
	value : 20,
	symbolSize : 80
}, {
	id : 11,
	category : 1,
	name : '数据表1',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 12,
	category : 1,
	name : '数据表2',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 13,
	category : 1,
	name : '数据表3',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 14,
	category : 1,
	name : '数据表4',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 15,
	category : 1,
	name : '数据表5',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 16,
	category : 1,
	name : '数据表6',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 17,
	category : 1,
	name : '数据表7',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 18,
	category : 1,
	name : '数据表8',
	symbol : 'rect',
	value : 20,
	symbolSize : 70
}, {
	id : 21,
	category : 2,
	name : '属性1',
	symbol : 'circle',
	value : 20,
	symbolSize : 60,
}, {
	id : 22,
	category : 2,
	name : '属性2',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 23,
	category : 2,
	name : '属性3',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 24,
	category : 2,
	name : '属性4',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 25,
	category : 2,
	name : '属性5',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 26,
	category : 2,
	name : '属性6',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
}, {
	id : 27,
	category : 2,
	name : '属性7',
	symbol : 'circle',
	value : 20,
	symbolSize : 60
} ];

//edges是其别名代表节点间的关系数据。
var linksData = [
	{
		source : 6,
		target : 0
	}, {
		source : 7,
		target : 0
	}, {
		source : 8,
		target : 0
	}, {
		source : 13,
		target : 6
	}, {
		source : 14,
		target : 6
	}, {
		source : 15,
		target : 7
	}, {
		source : 16,
		target : 8
	}, {
		source : 17,
		target : 8
	}, {
		source : 9,
		target : 12
	}, {
		source : 10,
		target : 13
	}
];