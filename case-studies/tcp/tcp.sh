#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -alph="[s,d,a]" -all -k=1000 -out=case-studies/tcp/TCP-DOM.txt

echo "Domain and BC1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -bc="F ((s && G ((((! a && (! d && s)) && X (((! a && (! d || s)) || (a && ! d)))) || ((! a && (! d || s)) || (a && ! d))))))" -alph="[s,d,a]" -all -k=1000 -out=case-studies/tcp/TCP-DOM-BC1.txt

echo "Domain and BC1 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -g="G(s -> (! a U d))" -g="! G(d -> (! s U a))" -bc="F ((s && G ((((! a && (! d && s)) && X (((! a && (! d || s)) || (a && ! d)))) || ((! a && (! d || s)) || (a && ! d))))))" -alph="[s,d,a]" -all -k=1000 -out=case-studies/tcp/TCP-DOM-BC1-G1.txt

echo "Domain and BC1 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -g="! G(s -> (! a U d))" -g="G(d -> (! s U a))" -bc="F ((s && G ((((! a && (! d && s)) && X (((! a && (! d || s)) || (a && ! d)))) || ((! a && (! d || s)) || (a && ! d))))))" -alph="[s,d,a]" -all -k=1000 -out=case-studies/tcp/TCP-DOM-BC1-G2.txt


echo "Domain and BC2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -bc="F ((d && G ((((! a && (! d && s)) && X ((! a || ! d))) || (! a || (! d && s))))))" -alph="[s,d,a]" -all -k=1000 -out=case-studies/tcp/TCP-DOM-BC2.txt

echo "Domain and BC2 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -g="G(s -> (! a U d))" -g="! G(d -> (! s U a))" -bc="F ((d && G ((((! a && (! d && s)) && X ((! a || ! d))) || (! a || (! d && s))))))" -alph="[s,d,a]" -all -k=1000 -out=case-studies/tcp/TCP-DOM-BC2-G1.txt

echo "Domain and BC2 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -g="! G(s -> (! a U d))" -g="G(d -> (! s U a))" -bc="F ((d && G ((((! a && (! d && s)) && X ((! a || ! d))) || (! a || (! d && s))))))" -alph="[s,d,a]" -all -k=1000 -out=case-studies/tcp/TCP-DOM-BC2-G2.txt
