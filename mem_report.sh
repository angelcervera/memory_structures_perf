#!/bin/bash

top -b -d 0.5 -p $1  | awk -v OFS="," '$1=="top"{ time=$3 } $1+0>0 { print time,$1,$NF,$5,$6; fflush() }'
