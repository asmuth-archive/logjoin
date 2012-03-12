desc "build"
task :build do
  %x(scalac *scala)
end

desc "run"
task :run do
  %x(scala Kollekt)
end

desc "cleanup"
task :clean do 
  %x(rm *.class)
end

desc "build and run"
task :buildrun => [:build, :run]

task :default => :buildrun
