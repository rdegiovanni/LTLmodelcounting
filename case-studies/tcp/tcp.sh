#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -alph="[s,d,a]" -all -k=20 -out=case-studies/tcp/TCP-DOM.txt

echo "Domain and BC1"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -bc="((s && G ((((! a && (! d && s)) && X (((! a && (! d || s)) || (a && ! d)))) || ((! a && (! d || s)) || (a && ! d))))))" -alph="[s,d,a]" -k=$K -out=case-studies/tcp/TCP-DOM-BC1.txt
done

echo "Domain and BC1 and G1 and not G2"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE && G(s -> (! a U d)) && ! G(d -> (! s U a))" -bc="((s && G ((((! a && (! d && s)) && X (((! a && (! d || s)) || (a && ! d)))) || ((! a && (! d || s)) || (a && ! d))))))" -alph="[s,d,a]" -k=$K -out=case-studies/tcp/TCP-DOM-BC1-G1.txt
done

echo "Domain and BC1 and not G1 and G2"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE && ! G(s -> (! a U d)) && G(d -> (! s U a))" -bc="((s && G ((((! a && (! d && s)) && X (((! a && (! d || s)) || (a && ! d)))) || ((! a && (! d || s)) || (a && ! d))))))" -alph="[s,d,a]" -k=$K -out=case-studies/tcp/TCP-DOM-BC1-G2.txt
done

echo "Domain and BC2"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -bc="((d && G ((((! a && (! d && s)) && X ((! a || ! d))) || (! a || (! d && s))))))" -alph="[s,d,a]" -k=$K -out=case-studies/tcp/TCP-DOM-BC2.txt
done

echo "Domain and BC2 and G1 and not G2"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE &&  G(s -> (! a U d)) && ! G(d -> (! s U a))" -bc="((d && G ((((! a && (! d && s)) && X ((! a || ! d))) || (! a || (! d && s))))))" -alph="[s,d,a]" -k=$K -out=case-studies/tcp/TCP-DOM-BC2-G1.txt
done

echo "Domain and BC2 and not G1 and G2"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE && ! G(s -> (! a U d)) && G(d -> (! s U a))" -bc="((d && G ((((! a && (! d && s)) && X ((! a || ! d))) || (! a || (! d && s))))))" -alph="[s,d,a]" -k=$K -out=case-studies/tcp/TCP-DOM-BC2-G2.txt
done
