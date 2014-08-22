中国农历计算程序
================

本程序利用天文数据推算二十四节气以及中国农历历法。目前已完成节气推算功能

计算节气时间可调用`oyyq.calendar.util.SolarTermsCalculator.getJulianDayInYearForTermOrder`计算得到。  
以上得到的时间是以地球时计算的儒略日，如需转换成实际时间，还需要转成UTC时间再转成日历时间。  
可参考`oyyq.calendar.util.SolarTermsCalculator.main`的处理。
