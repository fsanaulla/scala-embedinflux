#!/usr/bin/env bash
modules=(
    core scalaTest specs2
)

for md in "${modules[@]}"
do
   sbt ";project $md; ++$1; fullRelease"
done