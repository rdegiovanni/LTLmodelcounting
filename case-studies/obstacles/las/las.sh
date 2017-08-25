#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) -> l)" -alph="[m,o,f,s,l,b,g]" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM.txt

echo "Domain and O1 O2 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G(s))" -bc="F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O2O3.txt

echo "Domain and O1 O2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G(s))" -bc="F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O2.txt

echo "Domain and O1 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G(s))" -g="! F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O3.txt

echo "Domain and O2 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(s))" -bc="F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O2O3.txt

echo "Domain and O1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G(s))" -g="! F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1.txt

echo "Domain and O2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(s))" -bc="F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O2.txt

echo "Domain and O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(s))" -g="! F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O3.txt

echo "Domain and --"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="! F (m && G(s))" -g="! F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM--.txt


echo "Domain and G1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) -> l)" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-G1.txt

echo "Domain and G1 and O1 O2 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -bc="F (m && G(s))" -bc="F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O2O3-G1.txt

echo "Domain and G1 andO1 O2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -bc="F (m && G(s))" -bc="F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O2-G1.txt

echo "Domain and G1 andO1 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -bc="F (m && G(s))" -g="! F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1O3-G1.txt

echo "Domain and G1 andO2 O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -g="! F (m && G(s))" -bc="F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O2O3-G1.txt

echo "Domain and G1 andO1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -bc="F (m && G(s))" -g="! F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O1-G1.txt

echo "Domain and G1 andO2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -g="! F (m && G(s))" -bc="F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O2-G1.txt

echo "Domain and G1 andO3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -g="! F (m && G(s))" -g="! F (m && G (b))" -bc="F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM-O3-G1.txt

echo "Domain and G1 and --"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G(l -> (! f && ! g))" -alph="[m,o,f,s,l,b,g]" -g="G(m -> F(o))" -g="! F (m && G(s))" -g="! F (m && G (b))" -g="! F (m && G(l))" -all -k=100 -out=case-studies/obstacles/las-comb/LAS-DOM--G1.txt
