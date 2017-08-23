#!/bin/bash
echo "Domain and O1 O2 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G(s) && G (b) && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O2O3.txt

echo "Domain and O1 O2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(l))" -bc="F (m && G (s) && G(b))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O2.txt

echo "Domain and O1 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(b))" -bc="F (m && G (s) && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O3.txt

echo "Domain and O2 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(s))" -bc="F (m && G (b) && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O2O3.txt

echo "Domain and O1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(l))" -g="! F (m && G (b))" -bc="F (m && G(s))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1.txt

echo "Domain and O2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(s))" -g="! F (m && G (l))" -bc="F (m && G(b))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O2.txt

echo "Domain and O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(s))" -g="! F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O3.txt

echo "Domain and --"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(s))" -g="! F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM--.txt
