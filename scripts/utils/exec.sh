#! /bin/sh

if [ $# -lt 2 ]; then
  echo "Usage: exec.sh {target-machine} {path-to-shell-file} {args...}"
  exit 1
fi

target_host="root@$1"
shell_file="$2"
key_file=/godata/keys/deployer
target_shell_dir=/home/shells

exec_cmd="source $target_shell_dir/$(basename "$shell_file")"
for ((index = 3; index <= $#; index++)); do
  exec_cmd="$exec_cmd ${!index}"
done

ssh -i $key_file "$target_host" "mkdir -p $target_shell_dir" && \
  scp -i $key_file "$shell_file" "$target_host:$target_shell_dir/" && \
  ssh -i $key_file "$target_host" "$exec_cmd"
