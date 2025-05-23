#! /bin/sh

if ! [ $# -eq 2 ]; then
  echo "Usage: cmd.sh {target_host} {command}"
  exit 1
fi

target_host="root@$1"
command="$2"
key_file=/godata/keys/deployer

ssh -i $key_file "$target_host" "$command"
