//
//  String+Localization.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 22.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

extension String {
  var localized: String {
    return NSLocalizedString(self, comment: "\(self)_comment")
  }
  
  func localized(_ args: [CVarArg]) -> String {
    return localized(args)
  }
  
  func localized(_ args: CVarArg...) -> String {
    return String(format: localized, args)
  }
}
