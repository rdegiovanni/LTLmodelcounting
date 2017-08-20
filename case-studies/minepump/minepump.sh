#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((p && X(p)) -> X(X(! h)))" -alph="[p,m,h]" -all -k=100 -out=case-studies/minepump/MP-DOM.txt

echo "Domain and BC1"
#for K in {1..20}
#do
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((p && X(p)) -> X(X(! h)))" -bc="F (h && m)" -alph="[p,m,h]" -all -k=100 -out=case-studies/minepump/MP-DOM-BC1.txt
#done

echo "Domain and BC1 and G1 and not G2"
#for K in {1..20}
#do
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((p && X(p)) -> X(X(! h)))" -g="G(h -> X(p))" -g="! G(m -> X(! p))" -bc="F (h && m)" -alph="[p,m,h]" -all -k=100 -out=case-studies/minepump/MP-DOM-BC1-G1.txt
#done

echo "Domain and BC1 and not G1 and G2"
#for K in {1..20}
#do
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((p && X(p)) -> X(X(! h)))" -g="! G(h -> X(p))" -g="G(m -> X(! p))" -bc="F (h && m)" -alph="[p,m,h]" -all -k=100 -out=case-studies/minepump/MP-DOM-BC1-G2.txt
#done

echo "Domain and BC2"
#for K in {1..20}
#do
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((p && X(p)) -> X(X(! h)))" -bc="F ((h && ! m && p && X(((! h && ! p) || (h && (m || ! p))))))" -alph="[p,m,h]" -all -k=100 -out=case-studies/minepump/MP-DOM-BC2.txt
#done

echo "Domain and BC2 and G1 and not G2"
#for K in {1..20}
#do
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((p && X(p)) -> X(X(! h)))" -g="G(h -> X(p))" -g="! G(m -> X(! p))" -bc="F ((h && ! m && p && X(((! h && ! p) || (h && (m || ! p))))))" -alph="[p,m,h]" -all -k=100 -out=case-studies/minepump/MP-DOM-BC2-G1.txt
#done

echo "Domain and BC2 and not G1 and G2"
#for K in {1..20}
#do
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((p && X(p)) -> X(X(! h)))" -g="! G(h -> X(p))" -g="G(m -> X(! p))" -bc="F ((h && ! m && p && X(((! h && ! p) || (h && (m || ! p))))))" -alph="[p,m,h]" -all -k=100 -out=case-studies/minepump/MP-DOM-BC2-G2.txt
#done
