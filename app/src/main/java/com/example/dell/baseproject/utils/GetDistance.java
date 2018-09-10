package com.example.dell.baseproject.utils;

import java.text.DecimalFormat;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/7/21.
 * 邮箱：123123@163.com
 */

public class GetDistance {






   /* 这些经纬线是怎样定出来的呢？地球是在不停地绕地轴旋转（地轴是一根通过地球南北两极和地球中心的假想线），在地球中腰画一个与地轴垂直的大圆圈，使圈上的每一点都和南北两极的距离相等，这个圆圈就叫作“赤道”。在赤道的南北两边，画出许多和赤道平行的圆圈，就是“纬圈”；构成这些圆圈的线段，叫做纬线。我们把赤道定为纬度零度，向南向北各为90度，在赤道以南的叫南纬，在赤道以北的叫北纬。

    北极就是北纬90度，南极就是南纬90度。纬度的高低也标志着气候的冷热，如赤道和低纬度地地区无冬，两极和高纬度地区无夏，中纬度地区四季分明。
    其次，从北极点到南极点，可以画出许多南北方向的与地球赤道垂直的大圆圈，这叫作“经圈”；构成这些圆圈的线段，就叫经线。公元1884平面坐标图年，国际上规定以通过英国伦敦近郊的格林尼治天文台的经线作为计算经度的起点，即经度零度零分零秒，也称“本初子午线”。在它东面的为东经，共180度；在它西面的为西经，共180度。因为地球是圆的，所以东经180度和西经180度的经线是同一条经线。各国公定180度经线为“国际日期变更线”。为了避免同一地区使用两个不同的日期，国际日期变线在遇陆地时略有偏离。
    每一经度和纬度还可以再细分为60分，每一分再分为60秒以及秒的小数。利用经纬线，我们就可以确定地球上每一个地方的具体位置，并且把它在地图或地球仪上表示出来。例如问北京的经纬度是多少？我们很容易从地图上查出来是东经116度24分，北纬39度54分。在大海中航行的船只，只要把所在地的经度测出来，就可以确定船在海洋中的位置和前进方向。 纬度共有90度。赤道为0度，向两极排列，圈子越小，度数越大。
    横线是纬度，竖线是经度。
    当然可以计算，四元二次方程。
    经度和纬度都是一种角度。经度是个两面角，是两个经线平面的夹角。因所有经线都是一样长，为了度量经度选取一个起点面，经1884年国际会议协商，决定以通过英国伦敦近郊、泰晤士河南岸的格林尼治皇家天文台（旧址）的一台主要子午仪十字丝的那条经线为起始经线，称为本初子午线。本初子午线平面是起点面，终点面是本地经线平面。某一点的经度，就是该点所在的经线平面与本初子午线平面间的夹角。在赤道上度量，自本初子午线平面作为起点面，分别往东往西度量，往东量值称为东经度，往西量值称为西经度。由此可见，一地的经度是该地对于本初子午线的方向和角距离。本初子午线是0°经度，东经度的最大值为180°，西经度的最大值为180°，东、西经180°经线是同一根经线，因此不分东经或西经，而统称180°经线。
    纬度是个线面角。起点面是赤道平面，线是本地的地面法线。所谓法线，即垂直于参考扁球体表面的线。某地的纬度就是该地的法线与赤道平面之间的夹角。纬度在本地经线上度量，由赤道向南、北度量，向北量值称为北纬度，向南量值称为南纬度。由此可见，一地的纬度是该地对于赤道的方向和角距离。赤道是0°纬线，北纬度的最大值为90°，即北极点；南纬度的最大值为90°，即南极点。

    经纬度互换

    度(DDD)：E 108.90593度    N 34.21630度

    如何将度(DDD):： 108.90593度换算成度分秒(DMS)东经E 108度54分22.2秒?转换方法是将108.90593整数位不变取108(度),用0.90593*60=54.3558,取整数位54(分),0.3558*60=21.348再取整数位21(秒),故转化为108度54分21秒.

    同样将度分秒(DMS):东经E 108度54分22.2秒 换算成度(DDD)的方法如下:108度54分22.2秒=108+(54/60)+(22.2/3600)=108.90616度

    因为计算时小数位保留的原因，导致正反计算存在一定误差，但误差影响不是很大。1秒的误差就是几米的样子。GPS车友可以用上述方法换算成自己需要的单位坐标。

    经纬度换算成米

    纬度分为60分，每一分再分为60秒以及秒的小数。

    纬度线投射在图上看似水平的平行线，但实际上是不同半径的圆。有相同特定纬度的所有位置都在同一个纬线上。
    赤道的纬度为0°，将行星平分为南半球和北半球。
    纬度是指某点与地球球心的连线和地球赤道面所成的线面角，其数值在0至90度之间。位于赤道以北的点的纬度叫北纬，记为N，位于赤道以南的点的纬度称南纬，记为S。
    纬度数值在0至30度之间的地区称为低纬地区，纬度数值在30至60度之间的地区称为中纬地区，纬度数值在60至90度之间的地区称为高纬地区。
    赤道、南回归线、北回归线、南极圈和北极圈是特殊的纬线。
    纬度1秒的长度
    地球的子午线总长度大约40008km。平均：
    纬度1度 = 大约111km
            纬度1分 = 大约1.85km
            纬度1秒 = 大约30.9m

            根据地球上任意两点的经纬度计算两点间的距离

    地球是一个近乎标准的椭球体，它的赤道半径为6378.140千米，极半径为 6356.755千米，平均半径6371.004千米。如果我们假设地球是一个完美的球体，那么它的半径就是地球的平均半径，记为R。如果以0度经线为基 准，那么根据地球表面任意两点的经纬度就可以计算出这两点间的地表距离（这里忽略地球表面地形对计算带来的误差，仅仅是理论上的估算值）。设第一点A的经 纬度为(LonA, LatA)，第二点B的经纬度为(LonB, LatB)，按照0度经线的基准，东经取经度的正值(Longitude)，西经取经度负值(-Longitude)，北纬取90-纬度值(90- Latitude)，南纬取90+纬度值(90+Latitude)，则经过上述处理过后的两点被计为(MLonA, MLatA)和(MLonB, MLatB)。那么根据三角推导，可以得到计算两点距离的如下公式：

    C = sin(MLatA)*sin(MLatB)*cos(MLonA-MLonB) + cos(MLatA)*cos(MLatB)

    Distance = R*Arccos(C)*Pi/180

    这里，R和Distance单位是相同，如果是采用6371.004千米作为半径，那么Distance就是千米为单位，如果要使用其他单位，比如mile，还需要做单位换算，1千米=0.621371192mile

    如果仅对经度作正负的处理，而不对纬度作90-Latitude(假设都是北半球，南半球只有澳洲具有应用意义)的处理，那么公式将是：

    C = sin(LatA)*sin(LatB) + cos(LatA)*cos(LatB)*cos(MLonA-MLonB)

    Distance = R*Arccos(C)*Pi/180

    以上通过简单的三角变换就可以推出。

    如果三角函数的输入和输出都采用弧度值，那么公式还可以写作：

    C = sin(LatA*Pi/180)*sin(LatB*Pi/180) + cos(LatA*Pi/180)*cos(LatB*Pi/180)*cos((MLonA-MLonB)*Pi/180)

    Distance = R*Arccos(C)*Pi/180

    也就是：

    C = sin(LatA/57.2958)*sin(LatB/57.2958) + cos(LatA/57.2958)*cos(LatB/57.2958)*cos((MLonA-MLonB)/57.2958)

    Distance = R*Arccos(C) = 6371.004*Arccos(C) kilometer = 0.621371192*6371.004*Arccos(C) mile = 3958.758349716768*Arccos(C) mile

    在实际应用当中，一般是通过一个个体的邮政编码来查找该邮政编码对应的地区中心的经纬度，然 后再根据这些经纬度来计算彼此的距离，从而估算出某些群体之间的大致距离范围(比如酒店旅客的分布范围-各个旅客的邮政编码对应的经纬度和酒店的经纬度所 计算的距离范围-等等)，所以，通过邮政编码查询经纬度这样一个数据库是一个很有用的资源

    [java] view plain copy
*/

    /**
     * 测量方法来自百度地图
     * @author zimo2013
     *  http://blog.csdn.net/zimo2013
     *
     */

        private double DEF_PI = 3.14159265359; // PI
        private double DEF_2PI = 6.28318530712; // 2*PI
        private double DEF_PI180 = 0.01745329252; // PI/180.0
        private double DEF_R = 6370693.5; // radius of earth
        private GetDistance(){}
        private static GetDistance instance;

        public static synchronized GetDistance getInstance(){
            if(instance == null){
                instance = new GetDistance();
            }
            return instance;
        }
        /**
         * 返回为m，适合短距离测量
         *
         * @param lon1
         * @param lat1
         * @param lon2
         * @param lat2
         * @return
         */
        public String getShortDistance(double lon1, double lat1, double lon2, double lat2) {
            double ew1, ns1, ew2, ns2;
            double dx, dy, dew;
            double distance;
            // 角度转换为弧度
            ew1 = lon1 * DEF_PI180;
            ns1 = lat1 * DEF_PI180;
            ew2 = lon2 * DEF_PI180;
            ns2 = lat2 * DEF_PI180;
            // 经度差
            dew = ew1 - ew2;
            // 若跨东经和西经180 度，进行调整
            if (dew > DEF_PI)
                dew = DEF_2PI - dew;
            else if (dew < -DEF_PI)
                dew = DEF_2PI + dew;
            dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
            dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
            // 勾股定理求斜边长
            distance = Math.sqrt(dx * dx + dy * dy);
            return trans(distance);
        }

        /**
         * 返回为m,适合长距离测量
         *
         * @param lon1
         * @param lat1
         * @param lon2
         * @param lat2
         * @return
         */
        public String getLongDistance(double lon1, double lat1, double lon2, double lat2) {
            double ew1, ns1, ew2, ns2;
            double distance;
            // 角度转换为弧度
            ew1 = lon1 * DEF_PI180;
            ns1 = lat1 * DEF_PI180;
            ew2 = lon2 * DEF_PI180;
            ns2 = lat2 * DEF_PI180;
            // 求大圆劣弧与球心所夹的角(弧度)
            distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2)
                    * Math.cos(ew1 - ew2);
            // 调整到[-1..1]范围内，避免溢出
            if (distance > 1.0)
                distance = 1.0;
            else if (distance < -1.0)
                distance = -1.0;
            // 求大圆劣弧长度
            distance = DEF_R * Math.acos(distance);
            return trans(distance);
        }

        private String trans(double distance) {
            boolean isBig = false; // 是否为大于等于1000m
            if (distance >= 1000) {
                distance /= 1000;
                isBig = true;
            }
            return (new DecimalFormat(".00").format(distance)) + (isBig ? "千米" : "米");
        }


}
