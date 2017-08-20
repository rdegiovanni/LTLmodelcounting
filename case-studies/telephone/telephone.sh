#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(o -> ! f) && G(o -> ! c) && G(c -> ! f)" -alph="[o,c,d,f]" -all -k=20 -out=case-studies/telephone/TELEPHONE-DOM.txt

echo "Domain and BC1"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(o -> ! f) && G(o -> ! c) && G(c -> ! f)" -bc="F (((c && (! o && (! d && ! f))) && X (((! c && ((! o && ! d) || (o && (! d || f)))) || (c && (o || f))))))" -alph="[o,c,d,f]" -deph=1 -all -k=100 -out=case-studies/telephone/TELEPHONE-DOM-BC1.txt
#done

echo "Domain and BC1 and G1 and not G2"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(o -> ! f) && G(o -> ! c) && G(c -> ! f)" -g="G (c  -> (c  U (o || d)) )" -g="! G (c  ->  (c  U (f || d)) )" -bc="F (((c && (! o && (! d && ! f))) && X (((! c && ((! o && ! d) || (o && (! d || f)))) || (c && (o || f))))))" -alph="[o,c,d,f]" -deph=1 -all -k=100 -out=case-studies/telephone/TELEPHONE-DOM-BC1-G1.txt
#done

echo "Domain and BC1 and not G1 and G2"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(o -> ! f) && G(o -> ! c) && G(c -> ! f)" -g="! G (c  -> (c  U (o || d)) )" -g="G (c  ->  (c  U (f || d)) )" -bc="F (((c && (! o && (! d && ! f))) && X (((! c && ((! o && ! d) || (o && (! d || f)))) || (c && (o || f))))))" -alph="[o,c,d,f]" -deph=1 -all -k=100 -out=case-studies/telephone/TELEPHONE-DOM-BC1-G2.txt
#done
