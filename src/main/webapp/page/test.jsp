

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>星星评分</title>

</head>



<style type="text/css">

*{margin:0;padding:0;list-style-type:none;}

a,img{border:0;}

body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";color:#5e5e5e;}



.clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden}

.clearfix{display:inline-table}

*html .clearfix{height:1%}

.clearfix{display:block}

*+html .clearfix{min-height:1%}

.fl{float:left;}



.gradecon{border:1px solid #E0DCDC;background:#fefdea;padding:10px;width:320px;margin:40px auto 0 auto;}

.rev_pro li{line-height:20px;height:20px;}

.rev_pro li .revtit{text-align:right;display:block;float:left;margin-right:10px;width:70px;}

.revinp{float:left;display:inline;}

.level .level_solid,.level .level_hollow{float:left;background-image:url("../images/icon2.png");background-repeat:no-repeat;display:inline-block;width:15px;height:15px;}

.level .level_solid{background-position:0px 0px;}

.level .level_hollow{background-position:-21px 0px;}

.revgrade{margin-left:20px;}

</style>





<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>

<script type="text/javascript">

var degree = ['','很差','差','一般','满意','灰常满意','未评分'];
$(function(){
	$(document).on('mouseover','i[cjmark]',function(){
		var num = $(this).index();
		var list = $(this).parent().find('i');
		for(var i=0;i<=num;i++){
			list.eq(i).attr('class','level_solid');
		}
		for(var i=num+1,len=list.length-1;i<=len;i++){
			list.eq(i).attr('class','level_hollow');
		}
		$(this).parent().next().html(degree[num+1]);
	})

	//点击星星

	
})
</script>
<body>

			<div class="revinp">
				<span class="level">
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_hollow" cjmark=""></i>
				</span>

				<span class="revgrade"></span>

			</div>

</body>

</html>