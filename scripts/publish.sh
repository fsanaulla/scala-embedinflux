#!/usr/bin/env bash
modules=(
    core scalaTest spec2
)

for md in "${modules[@]}"
do
   sbt ";project $md; ++$1; fullRelease"
done