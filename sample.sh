while true
do
  ps -p 5504 -o %mem | sed 1d | tee -a mem.txt
  sleep 0.1
done