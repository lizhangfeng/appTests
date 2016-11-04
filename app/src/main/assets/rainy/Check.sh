if [[ $D = "a" ]];then
svc data disable
sleep 0.01
killall Tiny
for A in nat mangle filter;do
iptables -t $A -F;done;fi
if [[ $S = "a" ]];then
. ./*/*.conf
./*/Tiny -c ./*/*.conf
N="iptables -t nat -I OUTPUT"
P="iptables -t nat -I PREROUTING -s 192.168/16"
M="iptables -t mangle -I OUTPUT"
F="iptables -t filter -I OUTPUT"
for A in "$N" "$P";do
$A ! -p tcp -j REDIRECT --to 4444
$A -p udp ! --dport $dns_listen_port -j REDIRECT --to 4445
$A -p udp --dport 53 -j REDIRECT --to $dns_listen_port
$A -p tcp -j REDIRECT --to 4446
$A -p tcp ! --dport $listen_port -j REDIRECT --to 4447
$A -p tcp -j REDIRECT --to $listen_port;done
$N -m owner --uid-owner $uid -p tcp -j ACCEPT
for A in "$M" "$F";do
$A -p udp -m state --state NEW,ESTABLISHED -j ACCEPT
$A -p tcp --syn -m state --state NEW -j ACCEPT
$A -p tcp -m state --state ESTABLISHED -j ACCEPT;done
iptables -P FORWARD DROP
iptables -P OUTPUT DROP
. ./*/*.ini
./*/Kos >/dev/null 2>&1
for A in tun0 wlan0 lo;do
$M -o $A -j ACCEPT;
$F -o $A -j ACCEPT;done
$N -s 192.168/16 -j ACCEPT
$P -d 192.168/16 -j ACCEPT
sleep 0.1
svc data enable;fi
A=`pgrep Tiny`
if [[ $A != "" ]];then
echo "\n  🔔 Tiny  ";else
echo "\n  🔕 Tiny  ";fi
A=`iptables -nL | grep tcp`
if [[ $A != "" ]];then
echo "\n  🔓 Rainy 2.2  ";else
echo "\n  🔒 Rainy 2.2  ";fi
echo "           "
wk=`netcfg|grep -v /0|grep -v lo|grep UP|awk '{print $1}'`
up=`echo $(awk -v x=${xx} -v y=1048576 'BEGIN {printf "%.2f",x/y}')`
down=`echo $(awk -v x=${sx} -v y=1048576 'BEGIN {printf "%.2f",x/y}')`
ip=`netcfg | grep -v "/0" | grep -v "127.0.0.1" | tr -s " " | cut -d " " -f 3- | cut -d "/" -f 1`
mobile_model=`cat /system/build.prop | grep ro.product.model | cut -d "=" -f 2`
android_mc=`cat /system/build.prop | grep net.bt.name | cut -d "=" -f 2`
android_ver=`cat /system/build.prop | grep ro.build.version.release | cut -d "=" -f 2`
lcd_density=`cat /system/build.prop | grep ro.sf.lcd_density | cut -d "=" -f 2`

echo "⚡〖系统版本〗 ☁  $android_mc $android_ver"
echo "             "

echo "⚡〖手机型号〗 ☁  $mobile_model"
echo "             "

echo "⚡〖内网地址〗 ☁  $ip"
echo "             "      
echo "📝〖时间读取〗 ✏  `date +%Y年%m月%d日%T`" 
echo "             "
echo "🎊〖 DPI查询〗 ✏  $lcd_density"
echo "             "
echo "             "
echo "             "





echo "🌹感谢作者：Rainy

echo "             "
echo "             "
echo "             "
echo "             "

echo "\n $SD 模式检测 $SD"

echo "\n $XX 〖  端口 〗:$listen_port     $XX "

echo "\n $XX 〖dns解析〗:$dns_url        $XX "

