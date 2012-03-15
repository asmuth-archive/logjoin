desc "build"
task :build do
  %x{mkdir -p bin}
  exec "scalac -d bin src/*scala"
end

desc "run"
task :run do
  exec "cd bin && scala Kollekt"
end

desc "jar"
task :jar do
  scala_lib = ENV["SCALA_LIBJAR"] || "/usr/share/java/scala-library.jar"
  puts "using: #{scala_lib}, override with SCALA_LIBJAR=/path/to/lib.jar"

  File.open("MANIFEST.MF", "w+") do |f|
    f.write("Main-Class: Kollekt\n")
    f.write("Class-Path: #{scala_lib}\n")
  end

  exec "cd bin && jar -cfm ../kollekt.jar ../MANIFEST.MF *"
end

desc "cleanup"
task :clean do 
  exec "rm bin/* kollekt.jar MANIFEST.MF"
end

desc "build and jar"
task :buildjar do
  exec "rake build && rake jar"
end

desc "build and run"
task :buildrun do
  exec "rake build && rake run"
end

task :default => :buildjar
