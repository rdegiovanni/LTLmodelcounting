#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(s -> (p && q)) && G((p && q) -> s)" -alph="[p,q,r,s]" -all -k=1000 -out=case-studies/obstacles/or-pattern/DOM.txt

echo "Domain and O1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(s -> (p && q)) && G((p && q) -> s)" -alph="[p,q,r,s]" -bc="F(r && G(! p))" -all -k=1000 -out=case-studies/obstacles/or-pattern/DOM-O1.txt

echo "Domain and O2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(s -> (p && q)) && G((p && q) -> s)" -alph="[p,q,r,s]" -bc="F(r && G(! q))" -all -k=1000 -out=case-studies/obstacles/or-pattern/DOM-O2.txt

echo "Domain and O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(s -> (p && q)) && G((p && q) -> s)" -alph="[p,q,r,s]" -bc="F(r && F(p) && F(q) && G(! (p && q)) )" -all -k=1000 -out=case-studies/obstacles/or-pattern/DOM-O3.txt
