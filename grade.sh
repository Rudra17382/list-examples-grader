rm -rf $"student_dir"
git clone $1 $"student_dir" || exit 1 

# Total student score out of 35
score=0

function display_score(){

	printf "Student is given a score of $1/35 \n"
	exit 0
}

directory_for_junit=$PWD
classPath=".:$directory_for_junit/lib/hamcrest-core-1.3.jar:$directory_for_junit/lib/junit-4.13.2.jar"

set -o pipefail

cd ./$"student_dir"/

cp $directory_for_junit/"TestListExamples".java ./

printf "<<-- Starting Compilation -->>\n"

javac -cp $classPath *.java | tee ../output1.txt

if [[ $? -ne 0 ]]; then
	
	echo The student code did not compile.
	display_score $score

fi

score=$((score + 5))

printf "<<-- Running Java -->>\n" 

java -cp $classPath org.junit.runner.JUnitCore "TestListExamples" | tee ../output2.txt

if grep "OK" ../output2.txt; then
    score=$((score + 30))
    echo Student passed all tests!
elif grep -q -E -w "Tests run:|Failures:" ../output2.txt; then
    result=$(grep -E -w "Tests run:|Failures:" ../output2.txt)
    tests=$(echo $result | grep -Eo '[0-9]{1,10}')

    tests_run=$(echo $tests | cut -d' ' -f1) 
    fail=$(echo $tests | cut -d' ' -f2)
    pass=$((tests_run-fail))

    score=$((score + 30*pass/tests_run)) 
    echo Student passed $pass tests out of $tests_run tests
fi

display_score $score
