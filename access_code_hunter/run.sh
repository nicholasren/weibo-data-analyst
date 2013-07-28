#!//bin/sh
ruby run.rb > run.log & \
open http://127.0.0.1:4567
