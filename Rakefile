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
task :buildrun do
  exec "rake build && rake run"
end

task :default => :buildrun
