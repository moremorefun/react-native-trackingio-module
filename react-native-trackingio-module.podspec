require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-trackingio-module"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => "10.0" }
  s.source       = { :git => "https://github.com/moremorefun/react-native-trackingio-module.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,mm}"
  s.vendored_libraries = 'ios/libReYunTracking.a'
  s.frameworks = 'Security', 'CoreTelephony', 'AdSupport', 'SystemConfiguration', 'CoreMotion', 'iAd', 'AdServices', 'AVFoundation', 'CFNetwork', 'WebKit'
  s.libraries = 'sqlite3', 'z', 'resolv.9', 'resolv', 'c++'

  s.dependency "React-Core"
end
