#! /bin/bash

echo -e '\n--> usb-connect v_2022-02-13_10:00 <--\n'


u="usb0"


function __helper_fn { echo "--->" $cmd ; sleep 1 ; eval $cmd ; echo ; sleep 1 ; }


cmd="service avahi-daemon stop"
__helper_fn


cmd="service avahi-daemon status"
__helper_fn


cmd="ip link set $u down"
__helper_fn


cmd="ip link set $u up"
__helper_fn


cmd="ip link | grep --color=always '$u'"
__helper_fn


cmd="dhclient -v $u"
__helper_fn


sleep 2


cmd="ping -c 4 'www.google.com'"
__helper_fn


unset -f __helper_fn


echo -e "done!\n"


