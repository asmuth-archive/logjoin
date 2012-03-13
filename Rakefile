desc "build"
task :build do
  exec "scalac *scala"
end

desc "run"
task :run do
  exec "scala Kollekt"
end

desc "cleanup"
task :clean do 
  exec "rm *.class"
end

desc "build and run"
task :buildrun => [:build, :run]

task :default => :buildrun
