#!/bin/bash
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -alph="[p,q]" -all -k=100 -out=case-studies/rebuttal/DOM.txt

java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -bc="F(p && q)" -alph="[p,q]" -all -k=100 -out=case-studies/rebuttal/BC1.txt

java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -bc="F(! p)" -alph="[p,q]" -all -k=100 -out=case-studies/rebuttal/BC2.txt
