#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -alph="[p,m,l]" -all -k=1000 -out=case-studies/atm/ATM-DOM.txt


echo "Domain and BC1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -bc="F ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p)))))" -alph="[p,m,l]" -all -k=1000 -out=case-studies/atm/ATM-DOM-BC1.txt

echo "Domain and BC1 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) && X(l)))" -bc="F ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p)))))" -alph="[p,m,l]" -all -k=1000 -out=case-studies/atm/ATM-DOM-BC1-G1.txt

echo "Domain and BC1 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) && X(l)))" -bc="F ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p)))))" -alph="[p,m,l]" -all -k=1000 -out=case-studies/atm/ATM-DOM-BC1-G2.txt


echo "Domain and BC2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -bc="F (((l && ((! m) && (! p))) && X (((l && (m && p)) && X ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p) ))))))))" -alph="[p,m,l]" -all -k=1000 -out=case-studies/atm/ATM-DOM-BC2.txt

echo "Domain and BC2 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) && X(l)))" -bc="F (((l && ((! m) && (! p))) && X (((l && (m && p)) && X ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p) ))))))))" -alph="[p,m,l]" -all -k=1000 -out=case-studies/atm/ATM-DOM-BC2-G1.txt

echo "Domain and BC2 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) && X(l)))" -bc="F (((l && ((! m) && (! p))) && X (((l && (m && p)) && X ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p) ))))))))" -alph="[p,m,l]" -all -k=1000 -out=case-studies/atm/ATM-DOM-BC2-G2.txt


echo "Domain and BC3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -bc="F (((l && (m && p)) && X ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p)))))))" -alph="[p,m,l]" -deph=1 -all -k=1000 -out=case-studies/atm/ATM-DOM-BC3.txt

echo "Domain and BC3 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) && X(l)))" -bc="F (((l && (m && p)) && X ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p)))))))" -alph="[p,m,l]" -deph=1 -all -k=1000 -out=case-studies/atm/ATM-DOM-BC3-G1.txt

echo "Domain and BC3 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) && X(l)))" -bc="F (((l && (m && p)) && X ((((! l) && (((! m) && p) || (m && (! p)))) || (l && (m && (! p)))))))" -alph="[p,m,l]" -deph=1 -all -k=1000 -out=case-studies/atm/ATM-DOM-BC3-G2.txt



echo "Domain and BC4"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -bc="F ((l && G ((((l && ((! m) && (! p))) && X (((l && (m && p)) && X ((l || (((! m) && p) || (m && (! p)))))))) || (((l && ((! m) && (! p))) && X ((l && ((! m) || p)))) || (((l && (m && p)) && X (((l && ((! m) && (! p))) && X ((l && ((! m) || p)))))) || (((l && (m && p)) && X ((l || (((! m) && p) || (m && (! p)))))) || (l || (((! m) && p) || (m && (! p)))))))))))" -alph="[p,m,l]" -deph=1 -all -k=1000 -out=case-studies/atm/ATM-DOM-BC4.txt

echo "Domain and BC4 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) && X(l)))" -bc="F ((l && G ((((l && ((! m) && (! p))) && X (((l && (m && p)) && X ((l || (((! m) && p) || (m && (! p)))))))) || (((l && ((! m) && (! p))) && X ((l && ((! m) || p)))) || (((l && (m && p)) && X (((l && ((! m) && (! p))) && X ((l && ((! m) || p)))))) || (((l && (m && p)) && X ((l || (((! m) && p) || (m && (! p)))))) || (l || (((! m) && p) || (m && (! p)))))))))))" -alph="[p,m,l]" -deph=1 -all -k=1000 -out=case-studies/atm/ATM-DOM-BC4-G1.txt

echo "Domain and BC4 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) && X(l)))" -bc="F ((l && G ((((l && ((! m) && (! p))) && X (((l && (m && p)) && X ((l || (((! m) && p) || (m && (! p)))))))) || (((l && ((! m) && (! p))) && X ((l && ((! m) || p)))) || (((l && (m && p)) && X (((l && ((! m) && (! p))) && X ((l && ((! m) || p)))))) || (((l && (m && p)) && X ((l || (((! m) && p) || (m && (! p)))))) || (l || (((! m) && p) || (m && (! p)))))))))))" -alph="[p,m,l]" -deph=1 -all -k=1000 -out=case-studies/atm/ATM-DOM-BC4-G2.txt

