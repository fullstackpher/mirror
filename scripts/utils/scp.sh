#! /bin/sh

if ! [ $# -eq 3 ]; then
  echo "Usage: scp.sh {local-path} {target_host} {target_path}"
  exit 1
fi

local_path="$1"
target_host="root@$2"
target_path="$3"
key_file=/godata/keys/deployer

scp -i $key_file "$local_path" "$target_host:$target_path"
